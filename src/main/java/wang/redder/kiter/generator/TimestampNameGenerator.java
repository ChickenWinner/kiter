package wang.redder.kiter.generator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 时间戳名称生成器
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/3/29 22:13
 */
public class TimestampNameGenerator extends AbstractNameGenerator {


    public TimestampNameGenerator() {
    }

    ;

    public TimestampNameGenerator(int length, String prefix, String suffix) {
        super(length, prefix, suffix);
    }

    // 实现生成名称方法
    @Override
    public String generatorName() {
        // 当前时间 精确到毫秒
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        // 生成的时间戳字符串 + 后缀
        return rangLength(simpleDateFormat.format(date)) + suffix();
    }



}
