package wang.redder.kiter.generator;

/**
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/3/29 22:11
 */
public abstract class AbstractNameGenerator implements NameGenerator {

    // 最大文件名长度
    private static final int MAX_LENGTH = 32;

    // 默认文件名长度
    private int length = 10;

    // 默认无前缀
    private String prefix = "";

    // 默认无后缀
    private String suffix = "";

    public AbstractNameGenerator() { };

    public AbstractNameGenerator(int length, String prefix, String suffix) {
        if (length > 0) {
            this.length = length;
        }
        if (prefix != null) {
            this.prefix = prefix;
        }
        if (suffix != null) {
            this.suffix = suffix;
        }
    }

    @Override
    public int length() {
        return length <= MAX_LENGTH ? length : MAX_LENGTH;
    }

    @Override
    public String prefix() {
        return prefix;
    }

    @Override
    public String suffix() {
        return suffix;
    }
}
