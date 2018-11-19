package listeningrain.cn.blog.utils;

import java.security.MessageDigest;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/19 9:25
 * Description:
 */
public class EncryptUtils {

    //MD5加密
    public static String MD5(String originStr) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        try {
            byte[] btInput = originStr.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            String result = new String(str);
            //LOGGER.debug("Origin string: {}, md5 string: {}", originStr, result);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
