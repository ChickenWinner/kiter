package wang.redder.kiter.generator;

/**
 * 名称生成器抽象类
 * 实现了名称生成方法接口
 * 该抽象类提供了最大文件名长度，默认的文件名长度，默认的前缀以及后缀
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/3/29 22:11
 */
public abstract class AbstractNameGenerator implements NameGenerator {

    // 最大文件名长度，不包括后缀
    // 这里取32是因为UUID的最大长度为32
    private static final int MAX_LENGTH = 32;

    // 默认文件名长度
    // 这里取17是为了满足 时间戳到毫秒的长度要求
    private int length = 17;

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

    // 限制文件长度方法
    // 如果长度超过，则截取字符串，如果长度不够，补充不够的长度
    public String rangLength(String str) {
        // 如果长度不够
        if(str.length() < length()) {
            StringBuffer sb = new StringBuffer(str);
            for(int i = 0; i < length() - str.length(); i++) {
                sb.append(String.valueOf(i));
            }
            str = sb.toString();
            return str;
        }
        // 超过长度限制，截取;
        if(str.length() > length()) {
            return str.substring(str.length() - length());
        }
        // 没有超过 返回原字符串
        return str;
    }
}
