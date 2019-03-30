package wang.redder.kiter.generator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * UUID名称生成器
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/3/29 22:13
 */
public class UUIDNameGenerator extends AbstractNameGenerator {


    public UUIDNameGenerator() {
    }

    ;

    public UUIDNameGenerator(int length, String prefix, String suffix) {
        super(length, prefix, suffix);
    }

    // 实现生成名称方法
    @Override
    public String generatorName() {
        return rangLength(StringGenerator.getUUID(length(),"")) + suffix();
    }




}
