package com.vsu.login.controller;

import com.vsu.common.utils.MD5Utils;
import com.vsu.user.entity.User;
import com.vsu.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vsu on 2018/03/15.
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping
    public String login(){
        return "login";
    }

    @RequestMapping("/check")
    @ResponseBody
    public String checkLogin(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

//        记住用户
        //        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            SecurityUtils.getSubject().getSession().setTimeout(1800000);
        } catch (Exception e) {
            return "login_fail";
        }
        return "login_succ";

//        被shiro取代
//        User user = userService.findUserByName(userName);
//
//        //查询数据库
//        if (user != null){
//            if (MD5Utils.checkPassword(password, user.getPassword())){
//                //用户存在 设置session
//                request.getSession().setAttribute("userInfo", user);
//                return "login_success";
//            }else {
//                return "login_fail";
//            }
//        }else {
//            return "login_fail";
//        }

    }

    @RequestMapping("/register")
    @ResponseBody
    public String regiser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        userService.createUser(user);
        return "s";

    }

}
