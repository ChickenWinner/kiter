package wang.redder.kiter.generator;

/**
 * 名称生成器接口
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/3/29 22:01
 */
public interface NameGenerator {

    // 长度
    int length();
    // 前缀
    String prefix();
    // 后缀
    String suffix();
    // 生成名称方法
    String generatorName();

}
