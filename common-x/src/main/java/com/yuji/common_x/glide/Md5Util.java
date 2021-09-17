package com.yuji.common_x.glide;

import java.security.MessageDigest;

/**
 * @author W4255
 * @version [V1.0.0.000, 2021/1/26 0:47]
 * @Description: NA
 */
public class Md5Util {
    /***
     * MD5加码 生成32位md5码
     */
    public static String stringMd5(String inStr) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(inStr.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                //负数转换成正数
                int c = b & 0xff;
                //把十进制的数转换成十六进制的书
                String result = Integer.toHexString(c);
                if (result.length() < 2) {
                    //让十六进制全部都是两位数
                    sb.append(0);
                }
                sb.append(result);
            }
            return sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
