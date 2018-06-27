package com.vsu.user.service;

import com.vsu.common.utils.MD5Utils;
import com.vsu.user.dao.UserMapper;
import com.vsu.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vsu on 2018/03/15.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String userName) {
        User user = userMapper.selectByUserName(userName);
        return user;
    }

    @Override
    public void createUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        user.setPassword(MD5Utils.encrptyPassword(user.getPassword()));
        userMapper.insertSelective(user);
    }
}
