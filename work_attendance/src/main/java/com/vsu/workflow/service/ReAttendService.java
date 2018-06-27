package com.vsu.workflow.service;

import com.vsu.workflow.entity.ReAttend;

import java.util.List;

/**
 * Created by vsu on 2018/03/17.
 */
public interface ReAttendService {
    List<ReAttend> listReAttend(String username);

    void startReAttendFlow(ReAttend reAttend);

    List<ReAttend> listTasks(String userName);

    void approve(ReAttend reAttend);
}
