package wang.redder.kiter.generator;

/**
 * 文件名生成器
 * 这里包含了各种文件名生成方法
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/3/29 19:15
 */
public class FileNameGenerator {


    /**
     * <p>文件名生成器模板方法，负责调用传入的名称生成器实现类
     * 可以在调用方法前或者方法后做一些处理
     * 注意：用户如果想自定义文件名（当然生的字符串不一定要作为文件名）生成规则,可以有以下操作：
     * 1. 选择继承AbstractNameGenerator并实现其中的generatorName()方法
     *    这样文件的最大长度、默认长度、默认前缀、默认后缀已经固定
     * 2. 实现NameGenerator接口，这样用户可以发挥自己的创造力，自定义一个生成方式</p>
     * @param nameGenerator 名称生成器接口，这里实际要传入一个AbstractNameGenerator的实现类
     * @return 生成的字符串
     */
    public static String randomName(NameGenerator nameGenerator) {

        // do something before

        return nameGenerator.generatorName();

        // do something after
    }

    /**
     * 生成时间戳文件名
     * 使用默认的长度17，可以自定义后缀
     * @param suffix 后缀名
     * @return 文件名字符串
     */
    public static String timestampName(String suffix) {
        return randomName(new TimestampNameGenerator(0, null, suffix));
    }

    /**
     * 生成时间戳文件名
     * 使用自定义的长度，如果长度超过最大限制进行截取
     * 使用自定义的后缀名
     * @param suffix 后缀名
     * @return 文件名字符串
     */
    public static String timestampName(int length, String suffix) {
        return randomName(new TimestampNameGenerator(length, null, suffix));
    }


    /**
     * 生成UUID文件名
     * 使用默认长度17
     * 使用自定义的后缀名
     * @param suffix 后缀名
     * @return 文件名字符串
     */
    public static String UUIDName(String suffix) {
        return randomName(new UUIDNameGenerator(0, null, suffix));
    }

    /**
     * 生成UUID文件名
     * 使用自定义的长度，如果长度超过最大限制进行截取
     * 使用自定义的后缀名
     * @param suffix 后缀名
     * @return 文件名字符串
     */
    public static String UUIDName(int length, String suffix) {
        return randomName(new UUIDNameGenerator(length, null, suffix));
    }

}
