package wang.redder.kiter.generator;

import java.util.UUID;

/**
 * 字符串生成器
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/3/29 18:38
 */
public class StringGenerator {

    /**
     * 生成一个UUID数组，每一个元素都是一串UUID
     * 生成的数组长度可指定, 可选择是否替换'-'
     * @param number 数组长度
     * @param replaceStr 替换字符 有以下情况
     *                  1.null不替换  2.''，将'-'删除  3.其他字符，将'-'替换为该字符
     *
     * @return 一个UUID字符串数组
     */
    public static String[] getUUIDArray(int number, String replaceStr){
        if(number < 1){
            return null;
        }
        // UUID数组
        String[] retArray = new String[number];
        for(int i=0;i<number;i++){
            retArray[i] = getUUID(replaceStr);
        }
        return retArray;
    }


    /**
     * 生成一个UUID
     * 可根据需求替换掉UUID默认的'-'
     * @param replaceStr 替换字符 有以下情况
     *                  1.null不替换  2.''，将'-'删除  3.其他字符，将'-'替换为该字符
     * @return 一个UUID字符串
     */
    public static String getUUID(String replaceStr){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        if(replaceStr == null) {
            return uuid;
        } else if("".equals(replaceStr)) {
            return uuid.replaceAll("-", "");
        }
        return uuid.replaceAll("-", replaceStr);
    }

    /**
     * 生成指定长度的UUID
     * 如果指定的长度为0，反复一个空字符串
     * 如果指定的长度为负数，抛出异常
     * @param num UUID长度
     * @param replaceStr 替换字符 有以下情况
     *                  1.null不替换  2.''，将'-'删除  3.其他字符，将'-'替换为该字符
     * @return 一个UUID字符串
     */
    public static String getUUID(int num, String replaceStr){
        String uuid = getUUID(replaceStr);
        // 如果长度为空，返回一个空字符串
        if(num == 0) {
            return "";
        }
        // 如果长度小于0，抛出异常
        if(num < 0) {
            throw  new IllegalArgumentException("请求的长度为负数");
        } else if(num >= uuid.length()) {
            // 如果长度超过生成的UUID长度，就返回这个UUID
            return uuid;
        }
        // 截取指定长度
        return uuid.substring(0, num);
    }





}
