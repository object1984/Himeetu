package com.himeetu.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期处理工具类,常用的方法以get开头 <br/>
 * format date ->string <br/>
 * parse string->date
 */
public class DateUtils {
    /**
     * 取得当前时间的string 形式,样式yyyy-MM-dd hh:mm:ss
     *
     * @return
     */
    public static String getDate() {
        return format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回给给定日期的string形式,格式yyyy-MM-dd hh:mm:ss
     *
     * @param d
     * @return
     */
    public static String getDate(Date d) {
        return format(d, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 取得当前日期,年月日
     *
     * @return
     */
    public static String getDateyyyyMMdd() {
        return format(new Date(), "yyyyMMdd");
    }

    public static String getDateyyyyMMdd(Date d) {
        return format(d, "yyyyMMdd");
    }

    public static String getDateyyyy_MM_dd(Date d) {
        return format(d, "yyyy-MM-dd");
    }

    /**
     * 取得当前日期,年月日,格式yyyy-MM-dd
     *
     * @return
     */
    public static String getDateY_M_D() {
        return format(new Date(), "yyyy-MM-dd");
    }

    /**
     * 取得当前时间,时分秒
     *
     * @return
     */
    public static String getDateHMS() {
        return format(new Date(), "HH:mm:ss");
    }

    public static String getDateLoaclStr(Date d) {
        return format(d, "M-d");
    }

    /**
     * 将date类型格式化为style类型的字符串
     *
     * @param date
     * @param style
     * @return
     */
    public static String format(Date date, String style) {
        DateFormat df = new SimpleDateFormat(style);
        return df.format(date);
    }

    /**
     * 将dateStr解析为date类型
     *
     * @param dateStr
     * @return
     */
    public static Date parse(String dateStr, String style) {
        if (dateStr == null || style == null)
            return null;
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(style);
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * 将dateStr解析为date类型
     *
     * @param dateStr
     * @return
     */
    public static Date parse(String dateStr) {
        if (dateStr == null || "".equals(dateStr))
            return null;
        return DateUtils.parse(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parseDate(String dateStr) {
        if (dateStr == null || "".equals(dateStr))
            return null;
        return DateUtils.parse(dateStr, "yyyy-MM-dd");
    }

    public static Date parseTime(String dateStr) {
        if (dateStr == null || "".equals(dateStr))
            return null;
        return DateUtils.parse(dateStr, "yyyy-MM-dd HH:mm");
    }

    /**
     * 返回英文格式 like Jan 01,2009
     * <p/>
     * locale.English
     *
     * @param date
     * @return
     */
    public static String format2English(Date date) {
        return format("MMM dd,yyyy", Locale.ENGLISH, date);
    }

    public static String format(Date date) {
        return format("yyyy-MM-dd", Locale.CHINA, date);
    }

    public static String formatYear(Date date) {
        return format("yyyy年M月d日", Locale.CHINA, date);
    }

    public static String formatDay(Date date) {
        return format("M月d日", Locale.CHINA, date);
    }

    public static String formatTime(Date date) {
        return format("HH:mm", Locale.CHINA, date);
    }

    public static String format(String format, Locale locale, Date date) {
        DateFormat df = new SimpleDateFormat(format, locale);
        return df.format(date);
    }

    public static Date getMonthFirst(String str, String pattern) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateUtils.parse(str, pattern));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            return calendar.getTime();
        } catch (Exception e) {
            return null;
        }

    }

    public static Date getMonthEnd(String str, String pattern) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateUtils.parse(str, pattern));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            return calendar.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMonthFirstDay(String str, String pattern) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateUtils.parse(str, pattern));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            return DateUtils.getDateyyyy_MM_dd(calendar.getTime());
        } catch (Exception e) {
            return null;
        }

    }

    public static String getMonthEndDay(String str, String pattern) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateUtils.parse(str, pattern));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            return DateUtils.getDateyyyy_MM_dd(calendar.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getNextZeroDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getZeroDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static int getIntevelDays(Date beginDate, Date endDate) {
        Calendar c = Calendar.getInstance();
        int day = 0;
        Date bDate = getNextZeroDate(beginDate);
        Date eDate = getZeroDate(endDate);
        for (c.setTime(bDate); c.getTime().before(eDate); c.add(Calendar.DAY_OF_MONTH, 1)) {
            day++;
        }
        return day;
    }

    public static int getIntervalMonths(Date date) {
        Calendar now = Calendar.getInstance();
        Calendar start = Calendar.getInstance();
        start.setTime(date);
        return now.get(Calendar.MONTH) - start.get(Calendar.MONTH) + 12 * (now.get(Calendar.YEAR) - start.get(Calendar.YEAR));
        //				+ (now.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH) + 1)/now.get(Calendar.DAY_OF_MONTH);
    }


    public static Date getPreDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date getNextDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);
        return calendar.getTime();
    }

    /*获取星期几*/
    public static String getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    /**
     * DateFormat df = DateFormat.getDateInstance(DateFormat.FULL,
     * Locale.CHINA); String s = df.format(new Date()); System.out.println(s);
     * Full Thursday, July 29, 2010 2010年7月29日 星期四 long July 29, 2010 2010年7月29日
     * medium Jul 29, 2010 2010-7-29 short 7/29/10 10-7-29
     *
     * @param args
     */
    public static void main(String[] args) {
        //	System.out.println(DateUtil.getMonthFirstDay("2011-0AA19", "yyyy-MM"));
        //	System.out.println(DateUtil.getMonthEndDay("2011-02", "yyyy-MM"));

        //		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss MMM d,yyyy z", Locale.ENGLISH);
        //		try {
        //			System.out.println(sdf.parse("18:31:30 Jan 1,2000 PST"));
        //		} catch (ParseException e) {
        //			// TODO Auto-generated catch block
        //			e.printStackTrace();
        //		}
        //		System.out.println(DateUtils.getDate(new Date(0)));
        //		Date beginDate = DateUtil.parse("2012-06-29 00:00:00");
        //		System.out.println(getIntervalMonths(beginDate)%3 == 0);
        //		Calendar c = Calendar.getInstance();
        //		c.set(2012, 10, 5, 12, 30, 0);
        //		System.out.println(DateUtil.getDate(c.getTime()));
        //		c.set(2012, 10, 5, 13, 30, 0);
        //		System.out.println(DateUtil.getDate(c.getTime()));
    }

    public static Date getFirstDayByNextMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.MONTH, 1);
        return c.getTime();
    }

    public static Date getFirstDayByMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getMonthFirstDayByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }


    public static Date getCurrentDate() {
        return new Date();
    }

    public static Date getNextDayByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static Date getPreDayByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    public static Date getPreDayByDate(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DAY_OF_MONTH, -days);
        return c.getTime();
    }

    public static Date getPreDay000ByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DAY_OF_MONTH, -1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);

        return c.getTime();
    }

    public static Date getLastDayByMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    public static Date getDealDayByMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, 5, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.MONTH, 1);
        return c.getTime();
    }

    public static Date getLastMonthByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }

    public static Date get30DayZeroClock(Date date) {
        return getDayZeroClock(date, 30);
    }

    /**
     * 给定时间之前多少 天的时间
     *
     * @param date
     * @param beforeDay
     * @return
     */
    public static Date getDayZeroClock(Date date, int beforeDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, -beforeDay);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * @param date
     * @param calendarType
     * @return
     */
    public static int getInt(Date date, int calendarType) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(calendarType);
    }


    public static boolean isCurrentOrNextDate(String date) {
        String current = getDateyyyy_MM_dd(new Date());
        String next = getDateyyyy_MM_dd(getNextDate(new Date()));

        String wordDate = getDateyyyy_MM_dd(parseDate(date));
        if (wordDate.equals(current) || wordDate.equals(next)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBeforeDate(String date) {
        Date nowdate = new Date();
        String now = format(nowdate, "yyyy-MM-dd");
        String dateStr = format(parseDate(date), "yyyy-MM-dd");
        int flag = now.compareTo(dateStr);
        return flag > 0 ? true : false;
    }
}