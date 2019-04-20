import org.junit.Test;
import wang.redder.kiter.generator.FileNameGenerator;
import wang.redder.kiter.generator.StringGenerator;
import wang.redder.kiter.generator.TimestampNameGenerator;


/**
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/20 11:02
 */
public class GeneratorTest {

    // 测试字符串生成器
    @Test
    public void testStringGenerator() {
        // 生成UUID数组，指定生成个数以及替换UUID自带的'-'
        String[] strs = StringGenerator.getUUIDArray(2, "/");
        for(String s: strs) {
            /* 输出：06f6e20e/b1fe/47db/8305/011d7614a8dc
                    aaf83ab/b8da/4ec7/b6ef/d0865b244207 */
            System.out.println(s);
        }

        // 生成单个UUID，如果不想替换'-'可以选择传入''或者null
        System.out.println(StringGenerator.getUUID(null)); // 输出：8456e2c8-64c3-4448-93a7-ed97dca4ced9
        System.out.println(StringGenerator.getUUID("@")); // 输出：943fdcbe@bc52@4254@9f87@bdea2d2c1b01

        // 你也可以指定UUID的长度
        System.out.println(StringGenerator.getUUID(5, "")); // 输出：8b8df
        // 如果长度小于0，抛出异常
        System.out.println(StringGenerator.getUUID(-1, ""));// java.lang.IllegalArgumentException: 请求的长度为负数
    }

    // 测试文件名生成器
    @Test
    public void testFileNameGenerator() {

        // randomName方法 可以传入继承了AbstractNameGenerator抽象类的具体实现类
        // 该方法主要用于让用户自定义生成文件名方式
        System.out.println(
                FileNameGenerator.randomName(
                        new TimestampNameGenerator(0, null, "后缀")));// 输出：20190420112021724后缀

        // -----以下是一些默认实现类------

        // 根据时间戳生成文件名 使用默认长度17
        System.out.println(FileNameGenerator.timestampName(".jpg"));// 输出：20190420112639909.jpg
        // 你也可以手动指定长度
        System.out.println(FileNameGenerator.timestampName(5, ".png"));// 输出：39909.png

        // 根据UUID生成文件名
        System.out.println(FileNameGenerator.UUIDName(".jpg")); // 输出：867fa14ed75a4d729.jpg
        // 同样可以指定长度
        System.out.println(FileNameGenerator.UUIDName(6, ".png")); // 输出：4b370c.png
    }
}
