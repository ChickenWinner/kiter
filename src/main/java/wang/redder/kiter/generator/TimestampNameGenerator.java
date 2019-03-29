package wang.redder.kiter.generator;

/**
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/3/29 22:13
 */
public class TimestampNameGenerator extends AbstractNameGenerator {


    public TimestampNameGenerator(int length, String prefix, String suffix) {
        super(length, prefix, suffix);
    }

    // 覆盖生成名字方法
    @Override
    public String generatorName() {
        // TODO
        return String.valueOf(System.currentTimeMillis());
    }
}
