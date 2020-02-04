package com.immimg.immimg.util;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "verify")
@Data
public class VerifyUtils {
    @Resource
    private RedisTemplateUtils redis;


    private String host = "http://dingxin.market.alicloudapi.com";
    private String path = "/dx/sendSms";
    private String method = "POST";

    private String appcode;

    //验证码有效时间
    private Long timeout;

    //短信模板ID
    private String tplId;

    /**
     * 随机生成6位验证码
     * @return 验证码
     */
    private String createVerifyNo(){
        return ((int)((Math.random()*9+1)*100000))+"";
    }

    public boolean sendVerifyNo(String mobile) throws Exception {
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", mobile);//这里填写接收验证码的手机号
        String verifyNo = createVerifyNo();
        querys.put("param", "code:"+verifyNo);//验证码
        querys.put("tpl_id", tplId);
        Map<String, String> bodys = new HashMap<String, String>();
        HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
        String json = EntityUtils.toString(response.getEntity());
        System.out.println(json);
        Map<String, String> res = (Map<String, String>) JSON.parse(json);
        String returnCode = res.get("return_code");
        if ("00000".equals(returnCode)){
            return redis.set(mobile, verifyNo, timeout);
        }
        return false;
    }

    public String getVerifyNoByMobile(String mobile){
        return (String) redis.get(mobile);
    }
}
