import org.junit.Test;
import wang.redder.kiter.generator.FileNameGenerator;
import wang.redder.kiter.generator.TimestampNameGenerator;


/**
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/20 11:02
 */
public class GeneratorTest {

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
