package wang.redder.kiter.generator;

/**
 * 文件名生成器
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/3/29 19:15
 */
public class FileNameGenerator {



    public static String randomName(NameGenerator nameGenerator) {

        // do something before

        return nameGenerator.generatorName();

        // do something after
    }

    public static String timestampName() {
        return randomName(new TimestampNameGenerator(0, null, null));
    }


}
