package wang.redder.kiter.json;

import com.alibaba.fastjson.JSON;

/**
 * json工具类，内部使用fastJson
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/26 11:02
 */
public class JsonUtil {

    /**
     * bean转为String
     *
     * @param value 存储的值
     * @param <T>   泛型
     * @return 字符串
     */
    public static  <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else {
            if (clazz == String.class) {
                return (String) value;
            } else {
                if (clazz == long.class || clazz == Long.class) {
                    return "" + value;
                } else {
                    return JSON.toJSONString(value);
                }
            }
        }
    }

    /**
     * String转为bean
     *
     * @param str   字符串
     * @param clazz 转成的类型
     * @param <T>   泛型
     * @return 任一类型的值
     */
    @SuppressWarnings("unchecked")
    public static  <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else {
            if (clazz == String.class) {
                return (T) str;
            } else {
                if (clazz == long.class || clazz == Long.class) {
                    return (T) Long.valueOf(str);
                } else {
                    return JSON.parseObject(str, clazz);
                }
            }
        }
    }

}
