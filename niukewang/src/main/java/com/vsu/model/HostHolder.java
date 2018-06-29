package com.vsu.model;

import org.springframework.stereotype.Component;

/**
 * Created by vsu on 2018/02/11.
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<>();

    public User getUser(){
        return users.get();
    }

    public void setUsers(User user){
        users.set(user);
    }

    public void clear(){
        users.remove();
    }
}
