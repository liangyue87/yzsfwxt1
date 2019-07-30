package com.ljx.hfgsjt.util.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static final String yyyy_MM_dd_EN = "yyyy-MM-dd";
    public static final String yyyy_MM_dd_HH_mm_ss_EN = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_CN = "yyyy年MM月dd日";
    public static final String yyyy_MM_dd_HH_mm_ss_CN = "yyyy年MM月dd日HH时mm分ss秒";
    private static Map<String, DateFormat> dateFormatMap = new HashMap<String, DateFormat>();
    private static final TimeZone DEFAULT_ZONE = TimeZone.getTimeZone("GMT");
    /**
     * 功能描述：获取时间格式化对象
     *
     * @param formatStr
     * @return
     */
    public static DateFormat getDateFormat(String formatStr) {
        DateFormat df = dateFormatMap.get(formatStr);
        if (df == null) {
            df = new SimpleDateFormat(formatStr);
//            df.setTimeZone(DEFAULT_ZONE);
            dateFormatMap.put(formatStr, df);
        }
        return df;
    }

    /**
     * 2018-08-05T16:00:00.000Z 转成yyyy-MM-dd格式
     * @param create_time
     * @return
     * @throws ParseException
     */
    public static String getDateFormatByGMT(String create_time) throws ParseException{
        String format = "";
        if (create_time != null && create_time != "NULL" && create_time != "") {
            if (isDate(create_time)) {
                format = create_time;
            } else {
                //转换日期格式(将Mon Jun 18 2018 00:00:00 GMT+0800 (中国标准时间) 转换成yyyy-MM-dd)
                create_time = create_time.replace("Z", " UTC");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
                SimpleDateFormat sdf2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date d = new Date();
                if(create_time.indexOf("CST")!=-1){
                     d = (Date) sdf2.parse(create_time);
                }else{
                     d = sdf1.parse(create_time);//Mon Mar 06 00:00:00 CST 2017
                }
                format = sdf.format(d);//2017-03-06
            }
        }
        return format;
    }

    /**
     * 功能描述：根据时间格式获取当前时间
     *
     * @param date 当前时间
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, yyyy_MM_dd_HH_mm_ss_EN);
    }

    /**
     * 功能描述：根据时间格式获取当前时间
     *
     * @param date          当前时间
     * @param dateFormatStr 时间格式化字符串
     * @return
     */
    public static String dateToString(Date date, String dateFormatStr) {
        DateFormat format = getDateFormat(dateFormatStr);
        if (date != null) {
            return format.format(date);
        }
        return null;
    }


    /**
     * 功能描述：将字符串转为时间
     *
     * @param dateTimeStr 当前时间
     * @return
     */
    public static Date stringToDate(String dateTimeStr) {
        return stringToDate(dateTimeStr, yyyy_MM_dd_HH_mm_ss_EN);
    }

    /**
     * 功能描述：将字符串转为时间
     *
     * @param dateTimeStr   当前时间
     * @param dateFormatStr 时间格式化字符串
     * @return
     */
    public static Date stringToDate(String dateTimeStr, String dateFormatStr) {
        try {
            if ((dateTimeStr == null) || (dateTimeStr.equals(""))) {
                return null;
            }
            DateFormat format = getDateFormat(dateFormatStr);
            Date date = format.parse(dateTimeStr);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String weekdayOf(Date date) {
        final String dayNames[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek < 0) {
            dayOfWeek = 0;
        }
        return dayNames[dayOfWeek];
    }

    /**
     * 两个日期见的日期差距
     * @param begin
     * @param end
     * @return
     */
    public static long dayDiff(Date begin, Date end) {
        return Math.abs(daySubtract(begin, end));
    }



    /**
     * 用SimpleDateFormat计算时间差
     * @throws ParseException
     */
    public static String calculateTimeDifferenceBySimpleDateFormat(Date beginDate,Date endDate) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String result = "";
    /*天数差*/
        long from1 = beginDate.getTime();
        long to1 = endDate.getTime();
        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
        long daystime = (long)days*(1000 * 60 * 60 * 24);
    /*小时差*/
        int hours = (int) ((to1 - from1 - daystime) / (1000 * 60 * 60));
        long hourstime = (long)hours*(1000 * 60 * 60);
    /*分钟差*/
        int minutes = (int) ((to1 - from1-daystime-hourstime) / (1000 * 60));
        return days+"天"+hours+"小时"+minutes+"分钟";
    }

    /**
     * 两个日期见的日期差距
     * @param begin
     * @param end
     * @return
     */
    public static long daySubtract(Date begin, Date end) {
        return (end.getTime() - begin.getTime()) / (24 * 60 * 60 * 1000);
    }

    public static boolean isBetween(Date target, Date begin, Date end) {
        return begin.before(target) && target.before(end);
    }

    public static String timeDiffCn(Date date) {
        long minutes = Math.abs((new Date().getTime() - date.getTime()) / (60 * 1000));
        if (minutes < 1) {
            return "1分钟前";
        } else if (minutes < 60) {
            return minutes + "分钟前";
        } else if (minutes < 60 * 24) {
            return (minutes / 60) + "小时前";
        } else if (minutes < 60 * 24 * 30) {
            return (minutes / (60 * 24)) + "天前";
        } else if (minutes < 60 * 24 * 365) {
            return (minutes / (60 * 24 * 30)) + "个月前";
        } else {
            return (minutes / (60 * 24 * 365)) + "年前";
        }
    }

    public static Integer getWeekIndex(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static Integer getWeekIndex(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(stringToDate(date, yyyy_MM_dd_EN));
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getDay235959(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static int getYear(Date date) {
        return getField(date, Calendar.YEAR);
    }

    public static int getField(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return 7;
        } else {
            return dayOfWeek - 1;
        }
    }

    /**
     * 比较时间
     * @param time
     * @param current
     * @return
     */
    public static boolean isBeforeTime(String time, Date current) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date date = DateUtils.stringToDate(time, "HH:mm");
        return date.before(calendar.getTime());
    }


    /**
     * 获取今天第一秒
     * @param date
     * @return
     */
    public static Date getFirstSecondOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定时间最后一秒
     * @param date
     * @return
     */
    public static Date getLastSecondOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }


    /**
     * 获取本月第一天
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取本月最后一天
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取季度首月份
     * @param month
     * @return
     */
    private static int getMonthStartOfQuarter(int month) {
        return month / 3 * 3;
    }

    /**
     * 获取季度尾月份
     * @param month
     * @return
     */
    private static int getMonthEndOfQuarter(int month) {
        return month / 3 * 3 + 2;
    }

    /**
     * 获取本季度第一天
     * @param date
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.MONTH, getMonthStartOfQuarter(calendar.get(Calendar.MONTH)));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取本季度最后一天
     * @param date
     * @return
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, getMonthEndOfQuarter(calendar.get(Calendar.MONTH)));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取本年第一天
     * @param date
     * @return
     */
    public static Date getFirstDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取本年最后一天
     * @param date
     * @return
     */
    public static Date getLastDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 功能描述 判断日期格式和范围
     * @param date
     * @return
     */
    private static boolean isDate(String date){
        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(date);
        boolean dateType = mat.matches();
        return dateType;
    }

}
