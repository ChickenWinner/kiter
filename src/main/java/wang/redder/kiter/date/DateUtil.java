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


    private static String dateAdd(Date date, boolean includeDetailTime,
                                  int years, int months, int days,
                                  int hours, int minutes) throws ParseException {

        // 如果时间为空，则为当前时间
        if (date == null) {
            date = new Date();
        }
        // 不包含时分秒的处理
        if (!includeDetailTime) {
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_PATTERN);
            date = sdf.parse(sdf.format(date));
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
        if (hours != 0 && includeDetailTime) {
            cal.add(Calendar.HOUR, hours);
        }
        // 分钟加减，只有在包含时分秒的情况下才有效
        if (minutes != 0 && includeDetailTime) {
            cal.add(Calendar.MINUTE, minutes);
        }
        // 格式化
        if (includeDetailTime) {
            return dateFormat(cal.getTime(), "");
        } else {
            return dateFormat(cal.getTime(), DateUtil.DATE_PATTERN);
        }
    }



}
