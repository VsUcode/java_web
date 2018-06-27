package com.vsu.user.service;

import com.vsu.user.entity.User;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vsu on 2018/03/15.
 */

public interface UserService {
    User findUserByName(String userName);

    void createUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
