package com.example.demo.Controller;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/User")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping( "/login")//用户登录
    public ModelAndView SignIn(User user, HttpServletRequest request, HttpServletResponse response) {
        User u = userService.findByNameAndPassword(user);
        ModelAndView mv = new ModelAndView();
        if(u!=null){
            mv.addObject(u);
            // 重定向至根路径
            mv.setViewName("redirect:/");
            request.getSession().setAttribute("user", u);
            Cookie cookie=new Cookie("cookie_username",u.getUsername());
            cookie.setMaxAge(30*24*60*60);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
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
    @GetMapping("/logout")
    public ModelAndView SignOut(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv=new ModelAndView();
        request.getSession().removeAttribute("user");
        // 保存cookie，实现自动登录
        Cookie cookie_username = new Cookie("cookie_username", "");
        // 设置cookie的持久化时间，0
        cookie_username.setMaxAge(0);
        // 设置为当前项目下都携带这个cookie
        cookie_username.setPath(request.getContextPath());
        // 向客户端发送cookie
        response.addCookie(cookie_username);
        mv.setViewName("redirect:/");
        return mv;

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
