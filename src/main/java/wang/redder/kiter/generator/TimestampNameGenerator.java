package wang.redder.kiter.generator;

/**
 * 时间戳名称生成器
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/3/29 22:13
 */
public class TimestampNameGenerator extends AbstractNameGenerator {


    public TimestampNameGenerator(){};

    public TimestampNameGenerator(int length, String prefix, String suffix) {
        super(length, prefix, suffix);
    }

    // 实现生成名称方法
    @Override
    public String generatorName() {
        // TODO
        System.out.println(length());
        return String.valueOf(System.currentTimeMillis());
    }

}
