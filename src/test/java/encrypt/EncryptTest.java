package encrypt;

import org.junit.Test;
import wang.redder.kiter.encrypt.MD5encrypt;

/**
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/20 19:41
 */
public class EncryptTest {

    // 测试MD5加密
    @Test
    public void testMD5encrypt() {
        // 普通MD5加密
        System.out.println(MD5encrypt.getStrMD5("password")); // 输出：5f4dcc3b5aa765d61d8327deb882cf99

        // MD5加盐加密
        // 如果加入的盐值为""或null，默认用随机数做盐值
        System.out.println(MD5encrypt.getSaltMD5("password", "")); // 输出：{password=7493add49bf8b4e157d450f2f0b865d7, salt=4392854518933427}
    }
}
