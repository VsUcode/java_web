package com.vsu.attend.dao;

import com.vsu.attend.entity.Attend;
import com.vsu.attend.vo.QueryCondition;

import java.util.List;

public interface AttendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);

    Attend selectTodaySignRecord(Long userId);

    int countByCondition(QueryCondition condition);

    List<Attend> selectAttendPage(QueryCondition condition);

    List<Long> selectTodayAbsence();

    void batchInsert(List<Attend> list);

    List<Attend> selectTodayEveningAbsence();
}