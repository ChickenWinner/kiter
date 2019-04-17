package wang.redder.kiter.date;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/17 10:41
 */
public class DateUtil {

    // 格式化到秒
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    // 格式化到毫秒
    public static final String Millisecond_PATTERN = "yyyy-MM-dd HH:mm:ss SS";
    // 格式化到分
    public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    // 格式化到秒
    public static final String HOUR_PATTERN = "yyyy-MM-dd HH:mm:ss";
    // 格式化到天
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    // 格式化到月
    public static final String MONTH_PATTERN = "yyyy-MM";
    // 格式化到年
    public static final String YEAR_PATTERN = "yyyy";
    // 只有分
    public static final String MINUTE_ONLY_PATTERN = "mm";
    // 只有小时
    public static final String HOUR_ONLY_PATTERN = "HH";

    /**
     * 根据指定format格式化日期
     *
     * @param date   日期对象
     * @param format 格式字符串
     * @return 格式化后的日期字符串
     */
    public static String dateFormat(Date date, String format) {
        // 如果没有指定格式，使用默认的格式
        if (StringUtils.isBlank(format)) {
            format = DateUtil.DATE_TIME_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 日期加减操作
     *
     * @param date    日期对象
     * @param years   年
     * @param months  月
     * @param days    天
     * @param hours   时
     * @param minutes 分
     * @return 日期对象
     */
    public static Date dateAdd(Date date, int years, int months, int days,
                               int hours, int minutes) {

        // 如果时间为空，则为当前时间
        if (date == null) {
            date = new Date();
        }

        // 日历类操作加减
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 年份加减
        if (years != 0) {
            cal.add(Calendar.YEAR, years);
        }
        // 月份加减
        if (months != 0) {
            cal.add(Calendar.MONTH, months);
        }
        // 天数加减
        if (days != 0) {
            cal.add(Calendar.DATE, days);
        }
        // 小时加减，只有在包含时分秒的情况下才有效
        if (hours != 0) {
            cal.add(Calendar.HOUR, hours);
        }
        // 分钟加减，只有在包含时分秒的情况下才有效
        if (minutes != 0) {
            cal.add(Calendar.MINUTE, minutes);
        }

        return cal.getTime();
    }

    /**
     * 日期加减年份
     *
     * @param date  日期对象
     * @param years 年
     * @return 加减后的日期对象
     */
    public static Date dateAddYears(Date date, int years) {
        return dateAdd(date, years, 0, 0, 0, 0);
    }

    /**
     * 日期加减月份
     *
     * @param date   日期对象
     * @param months 月份
     * @return 加减后的日期对象
     */
    public static Date dateAddMonths(Date date, int months) {
        return dateAdd(date, 0, months, 0, 0, 0);
    }

    /**
     * 日期加减天数
     *
     * @param date 日期对象
     * @param days 天数
     * @return 加减后的日期对象
     */
    public static Date dateAddDays(Date date, int days) {
        return dateAdd(date, 0, 0, days, 0, 0);
    }

    /**
     * 日期加减小时
     *
     * @param date  日期对象
     * @param hours 小时
     * @return 加减后的日期对象
     */
    public static Date dateAddHours(Date date, int hours) {
        return dateAdd(date, 0, 0, 0, hours, 0);
    }

    /**
     * 日期加减分钟
     *
     * @param date    日期对象
     * @param minutes 分钟
     * @return 加减后的日期对象
     */
    public static Date dateAddMinutes(Date date, int minutes) {
        return dateAdd(date, 0, 0, 0, 0, minutes);
    }


    /**
     * 将日期字符串转为Date对象
     * 注意：这里字符串的格式需要与format的格式相同
     *
     * @param dateStr 日期字符串
     * @param format  日期格式
     * @return Date对象
     * @throws ParseException 转换异常
     */
    public static Date str2Date(String dateStr, String format) throws ParseException {
        if (StringUtils.isBlank(format)) {
            format = DateUtil.DATE_TIME_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr);
    }

    /**
     * 比较2个时间的大小，如果大于返回1，等于返回0，小于返回-1
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 比较结果
     */
    public static int compareDate(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        return calendar1.compareTo(calendar2);
    }

    /**
     * 获取时间差
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 结果数组, 包括天，小时，分钟，秒
     */
    public static long[] getBetweenTime(Date startDate, Date endDate) {
        long timesDis = Math.abs(startDate.getTime() - endDate.getTime());
        long day = timesDis / (1000 * 60 * 60 * 24);
        long hour = timesDis / (1000 * 60 * 60) - day * 24;
        long min = timesDis / (1000 * 60) - day * 24 * 60 - hour * 60;
        long sec = timesDis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
        return new long[]{day, hour, min, sec};

    }

    /**
     * 时间差文字描述
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 文字描述字符串
     */
    public static String getBetweenTimeStr(Date startDate, Date endDate) {
        long[] dis = getBetweenTime(startDate, endDate);
        StringBuilder sb = new StringBuilder();
        sb.append(dis[0]).append("天").append(dis[1]).append("小时").append(dis[2]).append("分")
                .append(dis[3]).append("秒");
        return sb.toString();
    }


}
