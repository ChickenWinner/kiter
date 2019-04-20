package date;

import org.junit.Test;
import wang.redder.kiter.date.DateUtil;

import java.util.Date;

/**
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/20 20:25
 */
public class DateTest {

    @Test
    public void testDateUtil() throws Exception {

        // 格式化日期，如果未指定格式则使用默认格式
        DateUtil.dateFormat(new Date(), "yyyy-MM-dd"); // 输出：2019-04-20

        // 日期加减  分别对应年 月 日 时 分 秒
        DateUtil.dateAdd(new Date(), 1, 1 ,1,1, 1); // 返回一个date对象
        // 当然也可以使用简单的操作，如年份加减
        DateUtil.dateAddYears(new Date(), 1);

        // 将字符串日期转为日期对象，注意格式要与字符串一致
        DateUtil.str2Date("2018-1-1", "yyyy-MM-dd");

        // 比较2个日期的大小
        DateUtil.compareDate(new Date(), new Date()); // 大于返回1，相等返回0，小于返回-1

        // 计算时间差
        // 返回时间差秒数
        DateUtil.getBetweenTime(new Date(), new Date()); // 输出：0
        // 返回时间差文字描述
        System.out.println(DateUtil.getBetweenTimeStr(new Date(), new Date())); // 输出：0天0小时0分0秒
    }
}
