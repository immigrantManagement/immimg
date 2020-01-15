package com.immimg.immimg.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @version v1.0
 * @ProjectName: springboot0110
 * @ClassName: Md5Utils
 * @Description: TODO(一句话描述该类的功能)
 * @Author: Nlqiong
 * @Date: 2020/1/12 15:44
 */
@Component
public class Md5Utils{

    /**
     * MD5加密
     * @param plainText 被处理的字符串
     * @param length 长度
     * @return
     */
    public String getMd5Str(String plainText,Integer length){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(plainText.getBytes());
            byte[] b = md5.digest();

            int i =0;
            StringBuilder builder = new StringBuilder("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if(i<0){
                    i+=256;
                }
                if(i<16){
                    builder.append(0);
                }
                builder.append(Integer.toHexString(i));
            }
            return builder.toString().substring(0,length);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
