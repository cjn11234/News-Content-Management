package com.example.demo.Controller;

import com.example.demo.Entity.New;
import com.example.demo.Entity.User;
import com.example.demo.Exception.EditException;
import com.example.demo.Service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/New")
public class NewController {
    @Autowired
    private NewService newService;
    @PostMapping(path = "/Add")
    public ModelAndView add(New article, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if (newService.exists(article)) {
            throw new EditException("新闻已存在");
        }
        article.setCreateTime( new Date(System.currentTimeMillis()));
        article.setUpdateTime(new Date(System.currentTimeMillis()));
        User u=(User)request.getSession().getAttribute("user");
        article.setAuthor(u.getUsername());
        newService.insert(article);
        // 重定向至根路径
        mv.setViewName("redirect:/New/main");
        return mv;
    }
    @GetMapping( "/Add")
    public ModelAndView add(){
        return new ModelAndView("new/editNew");
    }
    @GetMapping(path="/find/{author}")
    public ModelAndView find(@PathVariable("author") String author,Model model){
            List<New> u = newService.findByName(author);
            model.addAttribute("news",u);
            model.addAttribute("author",author);
            return new ModelAndView("new/authorDetail","newModel",model);
    }
    @GetMapping(path="/myInform")
    public ModelAndView find(HttpServletRequest request,Model model){
        User u=(User)request.getSession().getAttribute("user");
        List<New> n = newService.findByName(u.getUsername());
        model.addAttribute("news",n);
        model.addAttribute("user",u);
        return new ModelAndView("view","newModel",model);
    }
    @GetMapping(path="/main")
    public ModelAndView findAll(ModelAndView model){
        List<New> u = newService.findAll();

        model.addObject("news",u);
        model.setViewName("new/newList");
        return model;
    }

    @GetMapping(path="/look/{title}")
    public ModelAndView lookDetail(@PathVariable("title") String title,Model model){
        New n = newService.findByTitle(title);
        model.addAttribute("new",n);
        return new ModelAndView("new/newDetail","newModel",model);
    }
    @PostMapping(path="/search")
    public ModelAndView Search( String KeyValue,Model model){
        List<New> u;
        u = newService.search(KeyValue);
        model.addAttribute("news",u);
        return new ModelAndView("new/searchList","newModel",model);
    }



}
