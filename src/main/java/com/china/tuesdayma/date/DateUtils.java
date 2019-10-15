package com.china.tuesdayma.date;

import com.alibaba.fastjson.JSON;
import com.china.tuesdayma.string.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author: mzd
 * @Date: 2019/10/15 11:52
 */
public class DateUtils {

    public static String YEAR = "year";
    public static String MONTH = "month";
    public static String DAY = "day";
    public static String HOUR = "hour";
    public static String MINUTE = "minute";
    public static String SECOND = "second";


    public static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * data1比date2要早吗
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean dateBefore(Date date1, Date date2) {
        return date1.before(date2);
    }


    /**
     * data1比date2要晚吗
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean dateAfter(Date date1, Date date2) {
        return date1.after(date2);
    }

    /**
     * 将时间戳转换成时间字符串
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String long2String(long time, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DEFAULT_PATTERN;
        }
        Date date = long2Date(time);
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将时间戳转换成时间
     *
     * @param time
     * @return
     */
    public static Date long2Date(long time) {
        return new Date(time);
    }

    /**
     * 将时间字符串转换成时间
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String Date2String(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DEFAULT_PATTERN;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 获取指定的时间
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date getDesignationTime(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, second);
        return calendar.getTime();
    }

    /**
     * 获取指定时间的年月日，时分秒
     *
     * @param date
     * @return
     */
    public static HashMap<String, String> getTime4ymdhms(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(YEAR, calendar.get(Calendar.YEAR) + "");

        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            hashMap.put(MONTH, "0" + month);
        } else {
            hashMap.put(MONTH, month + "");
        }

        int day = calendar.get(Calendar.DATE);
        if (day < 10) {
            hashMap.put(DAY, "0" + day);
        } else {
            hashMap.put(DAY, day + "");
        }

        int hour = calendar.get(Calendar.HOUR);
        if (hour < 10) {
            hashMap.put(HOUR, "0" + hour);
        } else {
            hashMap.put(HOUR, hour + "");
        }

        int minute = calendar.get(Calendar.MINUTE);
        if (minute < 10) {
            hashMap.put(MINUTE, "0" + minute);
        } else {
            hashMap.put(MINUTE, minute + "");
        }

        int second = calendar.get(Calendar.SECOND);
        if (second < 10) {
            hashMap.put(SECOND, "0" + second);
        } else {
            hashMap.put(SECOND, second + "");
        }

        return hashMap;
    }

    /**
     * 获取指定时间是周几
     *
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        int weekBack = 0;
        /*星期日:Calendar.SUNDAY=1
         *星期一:Calendar.MONDAY=2
         *星期二:Calendar.TUESDAY=3
         *星期三:Calendar.WEDNESDAY=4
         *星期四:Calendar.THURSDAY=5
         *星期五:Calendar.FRIDAY=6
         *星期六:Calendar.SATURDAY=7 */
        switch (week) {
            case 1:
                weekBack = 7;
                break;
            case 2:
                weekBack = 1;
                break;
            case 3:
                weekBack = 2;
                break;
            case 4:
                weekBack = 3;
                break;
            case 5:
                weekBack = 4;
                break;
            case 6:
                weekBack = 5;
                break;
            case 7:
                weekBack = 6;
                break;
            default:
                break;
        }
        return weekBack;
    }


    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(getTime4ymdhms(null)));
    }
}
