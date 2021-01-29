package com.bntang666.java8;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;


/**
 * @description: 时间工具类
 * @Author: hcz
 * @CreateDt: 2019/9/30
 **/
public class DateUtils {
    /**
     * yyyyMMddHHmmss
     */
    public static final String YMDHMS = "yyyyMMddHHmmss";
    /**
     * 日期格式
     */
    public static final String DATE_FORMATTER = "yyyy-MM-dd";
    /**
     * 日期格式
     */
    public static final String DATE_FORMNEWEGG = "MM/dd/yyyy HH:mm:ss";
    /**
     * 发货单时间格式
     */
    public static final String DATE_SHIPMENT = "yyyyMMddHHmmss";

    /**
     * 移仓单时间格式
     */
    public static final String DATE_MOVE = "yyyyMMdd";

    /**
     * 日期格式
     */
    public static final String DATE_FORMVENDO = "MM/dd/yyyy";

    /**
     * 日期格式
     */
    public static final String DATE_FORMATTER_NO_RUNG = "yyyyMMdd";

    /**
     * 时间格式
     */
    public static final String DATETIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期小时
     */
    public static final String DATE_HOUR_FORMATTER = "yyyy-MM-dd HH:mm";

    /**
     * 小时分钟
     */
    public static final String DATE_HOUR_STR_FORMATTER = "HH点mm分";

    /**
     * 日期月份
     */
    public static final String DATE_MONTH_FORMATER = "yyyy-MM";

    /**
     * 中国标准时间
     * edg: Thu Feb 02 00:00:00 WET 2012.
     */
    public static final String DATE_EEE_FORMATER = "EEE MMM dd HH:mm:ss zzz yyyy";

    /**
     * 日期带开始时间'T'格式
     */
    public static final String DATE_T_FORMATER = "yyyy-MM-dd'T'HH:mm:ss";


    /**
     * 转成中文日期
     */
    public static final String DATE_STR_FORMATER = "yyyy年MM月dd日";

    /**
     * 日期时间字符串格式
     */
    public static final String DATETIME_STR_FORMATTER = "yyyy年MM月dd日 HH时mm分";

    /**
     * 日期字符串格式
     */
    public static final String DATE_STR_FORMATTER = "yyyy年MM月dd日";

    /**
     * 日期字符串格式
     */
    public static final String DATE_STR_FORMATTER1 = "yyyy/MM/dd";

    /**
     * 日期字符串格式
     */
    public static final String DATETIME_STR_FORMATTER1 = "yyyy/MM/dd HH:mm:ss";


    /**
     * 今天
     */
    private static final Integer TODAY = 0;

    private static final String DB_DEFAULT_TIME = "1000/01/01";

    /**
     * 时间进制
     */
    public static final Integer DAY_RADIX = 24;
    public static final Integer TIME_RADIX = 60;
    public static final Integer MILLISECONDS_RADIX = 1000;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_FORMATTER);
    private Date startTime;
    private Date intervalTime;
    private long threadId;
    private String threadStackTraceStr;


    public String debugString() {
        return threadStackTraceStr + "[ThreadID=" + threadId + "]: " + simpleDateFormat.format(startTime);
    }

    public String intervalString() {
        Date newDate = new Date();
        String msg = threadStackTraceStr + "[ThreadID=" + threadId + "]: " + simpleDateFormat.format(newDate) +
                " Interval[" + (newDate.getTime() - intervalTime.getTime()) +
                "ms] Total[" + (newDate.getTime() - startTime.getTime()) + "ms]";
        intervalTime = newDate;
        return msg;
    }


    /**
     * 获取两个日期相差的月数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 如果d2>d1返回 月数差 否则返回0
     */
    public static int getMonthDiff(Date startDate, Date endDate) {
        if (endDate == null || startDate == null) {
            return 0;
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(endDate);
        c2.setTime(startDate);
        if (c1.getTimeInMillis() < c2.getTimeInMillis()) {
            return 0;
        }
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d2 = 2015-8-16  d1 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d2的 月-日 小于 d1的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) {
            yearInterval--;
        }
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) {
            monthInterval--;
        }
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }


    /**
     * 比较两个时间相差的天数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Long getDaysDiff(LocalDate beginDate, LocalDate endDate) {
        return endDate.toEpochDay() - beginDate.toEpochDay();
    }

    /**
     * 比较两个时间相差的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getDaysDiff(Date startDate, Date endDate) {
        LocalDate beginTime = DateUtils.getDateToLocalDate(startDate);
        LocalDate endTime = DateUtils.getDateToLocalDate(endDate);
        return getDaysDiff(beginTime, endTime);
    }

    /**
     * 获取相差月份时的时间
     *
     * @param date  日期
     * @param month 距date 相差月份 正数加 负数减
     * @return
     */
    public static Date getDifferInByDate(Date date, int month) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        Date m = c.getTime();
        return m;
    }


    /**
     * 获取localDate日期的开始时间 {@link LocalTime}
     * eg. 2018-06-11 13:31:31 return 2018-06-11 00:00:00
     *
     * @param localDate
     * @return
     * @author wangfuguo
     */
    public static Date getDateBeginTime(LocalDate localDate) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MIN);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取localDate日期的结束时间 {@link LocalTime}
     * eg. 2018-06-11 13:31:31 return 2018-06-11 23:59:59.999999999
     *
     * @param localDate
     * @return
     * @author wangfuguo
     */
    public static Date getDateEndTime(LocalDate localDate) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MAX);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取localDate日期的中午12.00{{@link LocalTime}
     * eg 2019-03-28 14:32.36 retrun 2019-03-28 12:00:00
     *
     * @param localDate
     * @return
     * @author wugucheng
     */
    public static Date getDateNoon(LocalDate localDate) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.NOON);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * date 转 LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    /**
     * 获取localDate日期所在月的开始日期
     * eg. 2018-06-11 13:31:31 return 2018-06-01 00:00:00
     *
     * @param localDate
     * @return
     * @author wangfuguo
     */
    public static Date getMonthFirstDay(LocalDate localDate) {
        LocalDate firstDate = localDate.with(TemporalAdjusters.firstDayOfMonth());
        return getDateBeginTime(firstDate);
    }

    /**
     * 获取localDate日期所在月的结束日期
     * eg. 2018-06-11 13:31:31 return 2018-06-30 23:59:59.999999999
     *
     * @param localDate
     * @return
     * @author wangfuguo
     */
    public static Date getMonthLastDay(LocalDate localDate) {
        LocalDate firstDate = localDate.with(TemporalAdjusters.lastDayOfMonth());
        return getDateEndTime(firstDate);
    }


    /*    *//**
     * 日期转字符串
     *
     * @param date
     * @param formatter
     * @return
     *//*
    public static String dateToStringStr(Date date, String formatter) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isBlank(formatter)) {
            formatter = DATE_STR_FORMATER;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
        return simpleDateFormat.format(date);
    }*/

    /**
     * 毫秒值转日期
     *
     * @param time
     * @param formatter
     * @return
     */
    public static Date timeMillisToDate(long time, String formatter) {
        if (time == 0) {
            return null;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
            Date date = new Date();
            date.setTime(time);
            return date;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 字符串转日期
     *
     * @param dateString
     * @param formatter
     * @return
     */
    public static Date stringToDate(String dateString, String formatter) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 时间转10位时间戳
     *
     * @param time 2020-13-12 12:02:34
     * @return 234234234
     */
    public static Integer stringToTimestamp(String time) {
        int times = 0;
        try {
            times = (int) ((Timestamp.valueOf(time).getTime()) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (times == 0) {
        }
        return times;

    }

    /**
     * CST字符串转日期
     *
     * @param dateString
     * @return
     */
    public static Date stringCSTToDate(String dateString) {

        try {
            return new SimpleDateFormat(DATE_EEE_FORMATER, Locale.US).parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * java.util.Date --> java.time.LocalDate
     */
    public static LocalDate getDateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    /**
     * 得到两日期之间的毫秒数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long subDate(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        return date1.getTime() - date2.getTime();
    }

    /**
     * 计算已用耗时小时
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Long getTwoDateHour(Date date1, Date date2) {
        return getTwoDateTotalMin(date1, date2) / TIME_RADIX;
    }

    /**
     * 计算已用耗时分钟
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Long getTwoDateMin(Date date1, Date date2) {
        return getTwoDateTotalMin(date1, date2) % TIME_RADIX;
    }

    /**
     * 获取两个时间之间的分钟数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getTwoDateTotalMin(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0L;
        }
        long timeMils = date1.getTime() - date2.getTime();
        return timeMils / (MILLISECONDS_RADIX * TIME_RADIX);
    }

    /**
     * 根据分钟获取小时和分钟
     *
     * @param min
     * @return
     */
    public static String getHourAndMin(Long min) {
        if (min == null || min <= 0) {
            return "0小时0分";
        }
        return min / TIME_RADIX + "小时" + min % TIME_RADIX + "分钟";
    }

    /**
     * 根据毫秒计算天数(向上取整)
     *
     * @param milliseconds
     * @return
     */
    public static Long getDaysByMillisecondsCeil(Long milliseconds) {
        if (milliseconds == null) {
            return null;
        }
        Long radix = (long) DAY_RADIX * (long) TIME_RADIX * (long) TIME_RADIX * (long) MILLISECONDS_RADIX;
        if (milliseconds % radix != 0) {
            return milliseconds / radix + 1;
        }
        return milliseconds / radix;
    }

    /**
     * 判断是否是同一年
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameYear(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameYear = cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        return isSameYear;
    }

    /**
     * 判断是否是同一月
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameMonth(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameMonth = cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        return isSameMonth;
    }

    /**
     * 判断是否是同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameDay = cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        return isSameDay;
    }

    /**
     * 获取当前时间+1天（第二天凌晨）
     *
     * @param str num 天数
     * @return
     * @Author wugucheng
     */
    public static Date addMidDay(Date str, int num) {
        Calendar rightNow = Calendar.getInstance();
        Date formatStr = new Date();
        try {
            rightNow.setTime(str);
            rightNow.add(Calendar.DATE, num);
            formatStr = rightNow.getTime();
        } catch (Exception e) {
        }
        return formatStr;
    }

    /**
     * 获取时间文本
     *
     * @param hour
     * @param minute
     * @return
     */
    public static String getTimeString(Integer hour, Integer minute) {
        if (hour == null && minute == null) {
            return null;
        }
        if (hour == null) {
            return minute + "分钟";
        }
        if (minute == null) {
            return hour + "小时";
        }
        return hour + "小时" + minute + "分钟";
    }

    /**
     * 获取下 num 月时间
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addMidMonth(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        Date formatStr = new Date();
        try {
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, num);
            formatStr = calendar.getTime();
        } catch (Exception e) {
        }
        return formatStr;
    }

    /**
     * 日期转星期几
     *
     * @param date
     * @return -1表示无效
     */
    public static int dateToWeek(Date date) {
        if (date == null) {
            return -1;
        }
        int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        // 获得一个日历
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 指示一个星期中的某天。
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            return -1;
        }
        return weekDays[w];
    }

    /**
     * 返回date的时分
     *
     * @param date
     * @return
     */
    public static String getDateHourAndMinus(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(date);
    }

    /**
     * 返回date的年月日
     *
     * @param date
     * @return
     */
    public static String getDateYearAndMonthAndDay(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取单独的日并将date->int
     *
     * @param date
     * @return
     */
    public static int getDayCount(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        int day = cal.get(Calendar.DATE);
        return day;
    }

    /**
     * 获取当月总天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 将10 or 13 位时间戳转为时间字符串
     * convert the number 1407449951 1407499055617 to date/time format timestamp
     *
     * @param str_num
     * @param format
     * @return
     */
    public static String timestampToDate(String str_num, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (str_num.length() == 13) {
            String date = sdf.format(new Date(Long.parseLong(str_num)));
            return date;
        } else {
            String date = sdf.format(new Date(Integer.parseInt(str_num) * 1000L));
            return date;
        }
    }

    /**
     * 时间格式处理 算出距离今天的天数
     *
     * @param s
     * @return
     */
    public static Long getDays(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] split = s.split("/");
        Date parse = null;
        try {
            if (split[0].length() >= 4) {
                parse = format.parse(s);
            } else {
                s = split[2].substring(0, 4) + "-" + split[1] + "-" + split[0];
                parse = format.parse(s);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Instant instant = parse.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        LocalDate now = LocalDate.now();
        return Math.abs(now.toEpochDay() - localDate.toEpochDay());
    }

    public static String getDate(Date date) {
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf3.format(date);
        return format;
    }

    /**
     * 获取两个日期之间的所有日期
     *
     * @param timeSlot     包含 起始时间 结束时间
     * @param isContinuity 是否 0 ,1 连续
     * @return
     */
    public static List<String> getDays(String[] timeSlot, String isContinuity) {

        String startTime = timeSlot[0];
        String endTime = timeSlot[1];

        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            // 日期加1(包含结束)
            tempEnd.add(Calendar.DATE, +1);
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 拿到最近几天的日期
     *
     * @param days
     * @return
     */
    public static List<String> getDaysBetween(int days) {
        List<String> dayss = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.setTime(getDateAdd(days));
        Long startTIme = start.getTimeInMillis();
        Calendar end = Calendar.getInstance();
        end.setTime(new Date());
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24L;
        Long time = startTIme;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat(DATE_FORMATTER);
            dayss.add(df.format(d));
            time += oneDay;
        }
        return dayss;
    }

    private static Date getDateAdd(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -days);
        return c.getTime();
    }

    /**
     * 拿到今天日期
     *
     * @return
     */
    public static List<String> getToday() {
        return getDaysBetween(TODAY);
    }

    /**
     * 获取这个月第一天
     *
     * @return 这个月第一天
     */
    public static Date getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        //获得当前月第一天
        Date date = calendar.getTime();
        return calendar.getTime();
    }

    /**
     * 获取今天0点
     *
     * @return 今天0点
     */
    public static Date getEarlyToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past 获取过去的第几天
     * @return 过去第几天的日期
     */
    public static Date getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date today = calendar.getTime();
        return today;
    }


    /**
     * 得到当前小时是几点 精度到小时 eg:2020-4-23 13:34:00 ---> 2020-4-23 13:00:00
     *
     * @return 目前小时准点
     */
    public static Date getHourly() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = stringToDate(sdf.format(calendar.getTime()), DATETIME_FORMATTER);
        return date;
    }

    /**
     * 得到当前下小时是几点 精度到小时 eg:2020-4-23 13:34:00 ---> 2020-4-23 14:00:00
     *
     * @return 目前小时准点
     */
    public static Date getNextHourly() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = stringToDate(sdf.format(calendar.getTime()), DATETIME_FORMATTER);
        return date;
    }


    /**
     * 根据时间戳获取String类型的时间
     *
     * @param time 时间戳（ms）
     * @return
     */
    public static String getDateByLongTime(int time) {

        Date date = new Date(time * 1000L);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");

        return format.format(date);
    }


    /**
     * 格式化时间的原始方法
     *
     * @param date      日期
     * @param formatStr 格式
     * @return
     */
    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        String result = null;
        try {
            result = format.format(date);
        } catch (Exception e) {
            return "";
        }
        return result;
    }

    /**
     * 重置默认时间
     * 将默认为1000-01-01 00:00:00的时间重置为null
     *
     * @param time 时间
     * @return 结果
     */
    public static Date resetDefaultTime(Date time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_STR_FORMATTER1);
        String format = dateFormat.format(time);
        return DB_DEFAULT_TIME.equals(format) ? null : time;
    }

    /**
     * localdatetimel转成String
     *
     * @param ldt
     * @return
     */
    public static String ldt2Str(LocalDateTime ldt) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATETIME_FORMATTER);
        return dtf.format(ldt);
    }

    /**
     * 获取上个月月初0点时间
     *
     * @return 10位时间戳
     */
    public static Integer getLastMonthEarly() {
        // 前一个月第一天 0点
        Calendar lastOneC1 = Calendar.getInstance();
        lastOneC1.add(Calendar.MONTH, -1);
        lastOneC1.set(Calendar.DAY_OF_MONTH, 1);
        String lastMonthEarly = new SimpleDateFormat("yyyy-MM-dd").format(lastOneC1.getTime()) + " 00:00:00";
        return stringToTimestamp(lastMonthEarly);
    }

    /**
     * 获取上个月月初24点时间
     *
     * @return 10位时间戳
     */
    public static Integer getLastMonthLate() {
        //前一个月月末 24点
        Calendar lastOneC2 = Calendar.getInstance();
        lastOneC2.set(Calendar.DAY_OF_MONTH, 0);
        String lastMonthLate = new SimpleDateFormat("yyyy-MM-dd").format(lastOneC2.getTime()) + " 23:59:59";
        return stringToTimestamp(lastMonthLate);
    }
}