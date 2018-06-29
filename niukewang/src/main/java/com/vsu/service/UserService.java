package com.vsu.service;

import com.vsu.dao.LoginTicketDAO;
import com.vsu.dao.UserDAO;
import com.vsu.model.LoginTicket;
import com.vsu.model.User;
import com.vsu.util.NKWUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Encoder;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by vsu on 2018/02/10.
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    public Map<String, Object> register(String name, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<>();

        if (StringUtils.isBlank(name)){
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)){
            map.put("msg", "密码不能为空");
            return map;
        }

        User user = userDAO.selectByName(name);
        if (user != null){
            map.put("msg", "用户名已被注册");
            return map;
        }

        //密码强度
        user = new User();
        user.setName(name);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
        user.setHeadUrl(head);

        password += user.getSalt();
        String passwordResult= NKWUtils.MD5(password);
        user.setPassword(passwordResult);
        userDAO.addUser(user);

        // 登陆
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);

        return map;
    }

    public Map<String, Object> login(String name, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<>();

        if (StringUtils.isBlank(name)){
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)){
            map.put("msg", "密码不能为空");
            return map;
        }

        User user = userDAO.selectByName(name);
        if (user == null){
            map.put("msg", "用户名或密码错误");
            return map;
        }else{
            password += user.getSalt();
            String password_result= NKWUtils.MD5(password);
            if (!user.getPassword().equals(password_result)){
                map.put("msg", "用户名或密码错误");
                return map;
            }
        }

        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        map.put("userId", user.getId());
        return map;
    }


    private String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDAO.addTicket(ticket);
        return ticket.getTicket();
    }

    public User getUser(int id){
        return userDAO.selectById(id);
    }

    public void logout(String ticket){
        loginTicketDAO.updateStatus(ticket, 1);
    }

    public User selectByName(String name) {
        return userDAO.selectByName(name);
    }
}
