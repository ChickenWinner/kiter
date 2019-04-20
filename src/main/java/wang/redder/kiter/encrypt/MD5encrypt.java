package wang.redder.kiter.encrypt;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

/**
 * MD5加密
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/4 14:33
 */
public class MD5encrypt {


    /**
     * 普通MD5加密
     *
     * @param password 待加密字符串
     * @return 加密后的字符串
     */
    public static String getStrMD5(String password) {
        // 获取MD5实例
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        // 将加密字符串转换为字符数组
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        // 开始加密
        for (int i = 0; i < charArray.length; i++) {
        // 将每个字符转为字节
            byteArray[i] = (byte) charArray[i];
        }
        // 对字节数组加密
        byte[] digest = md5.digest(byteArray);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            int var = digest[i] & 0xff;
            if (var < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(var));
        }
        return sb.toString();
    }


    /**
     * 普通MD5加密版本2
     *
     * @param password 待加密字符串
     * @return 加密后的字符串
     */
    public static String getStrMD5V2(String password) {
        char hexDigits[] =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte strTemp[] = password.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte md[] = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * MD5加盐加密
     *
     * @param password 待加密字符串
     * @param salt 盐值 如果输入为空则随机加盐
     * @return 加密后的字符串以及所加盐值
     */
    public static Map<String, String> getSaltMD5(String password, String salt) {
        Random random = null;
        StringBuilder sBuilder = null;
        // 如果没有手动传入盐值
        if(StringUtils.isBlank(salt)) {
            // 生成一个16位的随机数
            random = new Random();
            sBuilder = new StringBuilder(16);
            sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
            int len = sBuilder.length();
            if (len < 16) {
                for (int i = 0; i < 16 - len; i++) {
                    sBuilder.append("0");
                }
            }
            salt = sBuilder.toString();
        }
        password =getStrMD5(password + salt);
        Map<String, String > map = new HashMap();
        map.put("password", password);
        map.put("salt", salt);
        return map;
    }


}
