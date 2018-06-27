package com.vsu.attend.service;

import com.vsu.attend.entity.Attend;
import com.vsu.attend.vo.QueryCondition;
import com.vsu.common.page.PageQueryBean;

/**
 * Created by vsu on 2018/03/15.
 */
public interface AttendService {
    void signAttend(Attend attend);

    PageQueryBean listAttend(QueryCondition condition);

    void checkAttend();
}
