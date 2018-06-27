package com.vsu.user.controller;

import com.vsu.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by vsu on 2018/03/15.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/userinfo")
    @ResponseBody
    public User getUser(HttpSession session){
//        User user = (User) session.getAttribute("userInfo");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        return user;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
//        session.invalidate();
        return "login";
    }
}
