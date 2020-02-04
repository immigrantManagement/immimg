package com.immimg.immimg.util;

import com.sun.org.apache.xerces.internal.impl.xs.util.XSNamedMap4Types;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @version v1.0
 * @ProjectName: springboot0110
 * @ClassName: TokenUtils
 * @Description: TODO(一句话描述该类的功能)
 * @Author: Nlqiong
 * @Date: 2020/1/12 15:18
 */
@Component
@ConfigurationProperties(prefix = "token")
@Data
public class TokenUtils {
    @Resource
    private Md5Utils md5Utils;
    @Resource
    private RedisTemplateUtils redisTemplateUtils;

    /**
     * MD5加密长度
     */
    private Integer length;
    /**
     * 时间格式
     */
    private String pattern;

    /**
     * 获取token
     * @param obj 用户信息
     * @param type 用户设备信息
     * @return
     */
    public String getToken(Object obj,String type){
        StringBuilder builder = new StringBuilder("");
        builder.append("token-");
        builder.append(type+"-");
        builder.append(md5Utils.getMd5Str(obj.toString(),length)+"-");
        builder.append(new SimpleDateFormat(pattern).format(new Date())+"-");
        builder.append(UUID.randomUUID().toString().substring(0,7));
        return builder.toString();
    }

    /**
     * 验证token 的一致性
     * @param token 客户端的token
     * @return
     */
    public boolean checkedToken(String token){
        if (redisTemplateUtils.get(token) == null){
            return false;
        }
        return true;
    }



}
