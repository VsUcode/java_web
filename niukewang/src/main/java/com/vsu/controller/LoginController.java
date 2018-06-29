package com.vsu.controller;

import com.vsu.async.EventModel;
import com.vsu.async.EventProducer;
import com.vsu.async.EventType;
import com.vsu.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by vsu on 2018/02/11.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    EventProducer eventProducer;


    @RequestMapping("/reg/")
    public String reg(Model model,
                      @RequestParam("name") String name,
                      @RequestParam("password") String password,
                      @RequestParam("next") String next,
                      @RequestParam(value = "rememberme", defaultValue = "false") boolean rememberme,
                      HttpServletResponse response){
        try {
            Map<String, Object> map = userService.register(name, password);
            if (map.containsKey("ticket")){
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if (rememberme){
                    cookie.setMaxAge(3600*24*5);
                }
                response.addCookie(cookie);
                if (StringUtils.isNotBlank(next)){
                    return "redirect:/" + next;
                }

                return "redirect:/";
            }else {
                model.addAttribute("msg", map.get("msg"));
                return "login";
            }
        }catch (Exception e){
            logger.error("注册异常" + e.getMessage());
            model.addAttribute("msg", "服务器错误");
            return "login";
        }

    }

    @RequestMapping("/reglogin")
    public String regloginPage(Model model,
                               @RequestParam(value = "next", required = false) String next) {

        model.addAttribute("next", next);
        return "login";
    }


    @RequestMapping("/login")
    public String login(Model model,
                      @RequestParam("name") String name,
                      @RequestParam("password") String password,
                      @RequestParam(value="next", required = false) String next,
                      @RequestParam(value = "rememberme", defaultValue = "false") boolean rememberme,
                      HttpServletResponse response){
        try {
            Map<String, Object> map = userService.login(name, password);
            if (map.containsKey("ticket")){
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if (rememberme){
                    cookie.setMaxAge(3600*24*5);
                }
                response.addCookie(cookie);

                eventProducer.fireEvent(new EventModel(EventType.LOGIN)
                        .setExt("username", name).setExt("email", "1379700109@qq.com")
                        .setActorId((int)map.get("userId")));

                if (StringUtils.isNotBlank(next)){
                    return "redirect:/" + next;
                }

                return "redirect:/";
            }else {
                model.addAttribute("msg", map.get("msg"));
                return "login";
            }
        }catch (Exception e){
            logger.error("登录异常" + e.getMessage());
            return "login";
        }

    }


    @RequestMapping("/logout")
    public String logout(@CookieValue("ticket") String ticket){
        userService.logout(ticket);
        return "redirect:/";
    }

}
