package wang.redder.kiter.generator;

/**
 * 名称生成抽象接口
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/3/29 22:01
 */
public interface NameGenerator {

    int length();

    String prefix();

    String suffix();

    String generatorName();
}
