package com.vsu.common.task;

import com.vsu.attend.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by vsu on 2018/03/17.
 */
public class AttendCheckTask {

    @Autowired
    private AttendService attendService;

    public  void checkAttend(){
        attendService.checkAttend();
    }
}
