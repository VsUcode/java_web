package com.vsu.controller;

import com.vsu.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by vsu on 2018/02/03.
 */
//@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "Hello";
    }


//    @RequestMapping(path="/profile/{groupId}/{userId}")
//    @ResponseBody
//    public String profile(@PathVariable("userId") int userId,
//                          @PathVariable("groupId") String groupId,
//                          @RequestParam(value = "type", defaultValue = "1") int type,
//                          @RequestParam(value = "key", defaultValue = "qwe", required = false) String key){
//        return ("Profile Page of " +groupId +" "+ userId + " type: " + type + " key: " + key);
//    }


//    @RequestMapping(path = {"/vm"}, method = {RequestMethod.GET})
    @RequestMapping("/ftl")
    public String template(Model model){
        model.addAttribute("value1", "qwer");
        User user = new User("vsu");
        model.addAttribute("user", user);
        return "home";
    }


}
























