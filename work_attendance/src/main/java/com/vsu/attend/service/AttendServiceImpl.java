package com.vsu.attend.service;

import com.vsu.attend.dao.AttendMapper;
import com.vsu.attend.entity.Attend;
import com.vsu.attend.vo.QueryCondition;
import com.vsu.common.page.PageQueryBean;
import com.vsu.common.utils.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by vsu on 2018/03/15.
 */
@Service
public class AttendServiceImpl implements AttendService {
    private Log log = LogFactory.getLog(AttendServiceImpl.class);

    /**
     * 中午十二点 判定上下午
     */
    private static final int NOON_HOUR = 12;
    private static final int NOON_MINUTE = 00;

    /**
     * 早晚上班时间判定
     */
    private static final int MORNING_HOUR = 9;
    private static final int MORNING_MINUTE = 30;
    private static final int EVENING_HOUR = 18;
    private static final int EVENING_MINUTE = 30;

    /**
     * 缺勤一整天
     */
    private static final Integer ABSENCE_DAY = 480;
    /**
     * 考勤异常状态
     */
    private static final Byte ATTEND_STATUS_ABNORMAL = 2;
    private static final Byte ATTEND_STATUS_NORMAL = 1;


    @Autowired
    private AttendMapper attendMapper;

    /**
     * @Author: vsu
     * @Descriptioon: 签到
     * @Date: 2018/03/15
     */
    @Override
    @Transactional  //事务
    public void signAttend(Attend attend) {
        try {
            Date today = new Date();
            attend.setAttendDate(today);
            attend.setAttendWeek((byte) DateUtils.getTodayWeek());

            //查询当天是否打卡
            Attend todayRecord = attendMapper.selectTodaySignRecord(attend.getUserId());
            Date noon = DateUtils.getDate(NOON_HOUR, NOON_MINUTE);
            Date morningAttend = DateUtils.getDate(MORNING_HOUR, MORNING_MINUTE);

            //如果今天还没打卡
            if (today == null) {
                //判断应该在那个时间段打卡
                // 早于12点， 上午打卡
                if (today.compareTo(noon) <= 0) {
                    attend.setAttendMoring(today);

                    //计算是否迟到
                    //如果迟到
                    if (today.compareTo(morningAttend) > 0) {
                        attend.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                        attend.setAbsence(DateUtils.getMinutes(morningAttend, today));
                    }
                } else { //晚于12点 下午打卡
                    attend.setAttendEvening(today);
                }
                attendMapper.insertSelective(attend);
            } else { //今天已打卡
                // 打卡时间 早于12点 上午打卡
                if (today.compareTo(noon) <= 0) {
                    return;
                } else {
                    todayRecord.setAttendEvening(today);
                    //计算是否早退
                    Date eveningAttend = DateUtils.getDate(EVENING_HOUR, EVENING_MINUTE);
                    //早于下午六点半 早退
                    if (today.compareTo(eveningAttend) < 0) {
                        todayRecord.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                        todayRecord.setAbsence(DateUtils.getMinutes(today, eveningAttend));
                    } else {
                        todayRecord.setAttendStatus(ATTEND_STATUS_NORMAL);
                        todayRecord.setAbsence(0);
                    }
                    attendMapper.updateByPrimaryKeySelective(todayRecord);
                }
            }
        }catch (Exception e){
            log.error("签到异常");
            throw e;
        }

    }

    @Override
    public PageQueryBean listAttend(QueryCondition condition) {

        //根据条件查询 count记录数目
        int count = attendMapper.countByCondition(condition);
        PageQueryBean pageQueryBean = new PageQueryBean();

        //如果有记录 才去查询分页数据 没有相关记录数目 没必要去查分页数据
        if (count > 0 ){
            pageQueryBean.setTotalRows(count);
            pageQueryBean.setCurrentPage(condition.getCurrentPage());
            pageQueryBean.setPageSize(condition.getPageSize());
            List<Attend> attendList =  attendMapper.selectAttendPage(condition);
            pageQueryBean.setItems(attendList);
        }

        return pageQueryBean;
    }


    /**
     * @Author: vsu
     * @Descriptioon: 检查异常数据
     * @Date: 2018/03/17
     */
    @Override
    @Transactional
    public void checkAttend() {

        //查询缺勤用户ID 插入打卡记录  并且设置为异常 缺勤480分钟
        List<Long> userIdList = attendMapper.selectTodayAbsence();
        if (CollectionUtils.isNotEmpty(userIdList)){
            List<Attend> list = new ArrayList<>();
            for(Long userId:userIdList){
                Attend attend = new Attend();
                attend.setUserId(userId);
                attend.setAttendDate(new Date());
                attend.setAttendWeek((byte)DateUtils.getTodayWeek());
                if (attend.getAttendWeek() != 6 && attend.getAttendWeek() != 7){
                    attend.setAbsence(ABSENCE_DAY);
                    attend.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                } else {
                    attend.setAttendStatus(ATTEND_STATUS_NORMAL);
                }
                list.add(attend);
            }
            attendMapper.batchInsert(list);
        }

       // 检查晚打卡 将下班未打卡记录设置为异常
        List<Attend> absenceList = attendMapper.selectTodayEveningAbsence();
        if(CollectionUtils.isNotEmpty(absenceList)){
            for(Attend attend : absenceList){
                attend.setAbsence(ABSENCE_DAY);
                attend.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                attendMapper.updateByPrimaryKeySelective(attend);
            }
        }

    }


}
