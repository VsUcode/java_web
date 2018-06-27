package com.vsu.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by vsu on 2018/03/15.
 */
public class DateUtils {

    private static Calendar calendar = Calendar.getInstance();

    /**
     * @Author: vsu
     * @Descriptioon: 今天是周几
     * @Date: 2018/03/15
     */
    public static int getTodayWeek(){
        calendar.setTime(new Date());
        int week = calendar.get(Calendar.DAY_OF_WEEK ) - 1;
        if (week < 0){
            week = 7;
        }

        return week;
    }


    /**
     * @Author: vsu
     * @Descriptioon: 获取当天某个时间
     * @Date: 2018/03/15
     */
    public static Date getDate(int hour, int minute){
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }


    /**
     * @Author: vsu
     * @Descriptioon: 计算时间差，分钟
     * @Date: 2018/03/15
     */
    public static int getMinutes(Date startDate, Date endDate) {
        long start = startDate.getTime();
        long end = endDate.getTime();
        int minute = (int) ((end - start) / (60 * 1000));

        return minute;
    }
}
