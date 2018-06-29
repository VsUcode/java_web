package com.vsu.service;

import org.springframework.stereotype.Service;

/**
 * Created by vsu on 2018/04/13.
 */
@Service
public class WendaService {
    public String getMessage(int userId) {
        return "Hello Message:" + String.valueOf(userId);
    }
}
