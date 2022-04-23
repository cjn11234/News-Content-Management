package com.example.demo.Controller;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/User")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping( "/login")//用户登录
    public ModelAndView SignIn(User user, HttpServletRequest request) {
        User u = userService.findByNameAndPassword(user);
        ModelAndView mv = new ModelAndView();
        if(u!=null){
            mv.addObject(u);
            // 重定向至根路径
            mv.setViewName("redirect:/");
            request.getSession().setAttribute("user", u);

        }
        else {
            mv.setViewName("login");
        }
        // 添加user到视图
        return mv;

    }

    @GetMapping( "/login")
    public ModelAndView SignIn() {
        return new ModelAndView("login");
    }

    @GetMapping( "/register")//用户注册
    public ModelAndView SignUp(){
        return new ModelAndView("register");
    }
    @PostMapping( "/register")//用户注册
    public ModelAndView SignUp(User user) {
        ModelAndView mv = new ModelAndView();

        userService.insert(user);
        // 重定向至根路径
        mv.setViewName("redirect:/");
        return mv;
    }

}
