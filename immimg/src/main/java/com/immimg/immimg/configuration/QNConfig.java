package com.immimg.immimg.configuration;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version v1.0
 * @ProjectName: springboot0110
 * @ClassName: QNConfig
 * @Description: TODO(一句话描述该类的功能)
 * @Author: Nlqiong
 * @Date: 2020/1/13 17:30
 */
@ConfigurationProperties("qiniu")
@Configuration
@Data
public class QNConfig{
    //七牛云认证是需要的密钥
    private String accessKey;
    private String secretKey;

    /**
     * 指定七牛云服务器所在地区
     * @return
     */
    @Bean
    public com.qiniu.storage.Configuration qiniuConfig(){
        return new com.qiniu.storage.Configuration(Zone.zone0());
    }

    /**
     * 创建上传文件的方法
     * @return
     */
    @Bean
    public UploadManager uploadManager(){
        return new UploadManager(qiniuConfig());
    }

    /**
     * 创建向七牛云进行认证的方法
     * @return
     */
    @Bean
    public Auth auth(){
        return Auth.create(accessKey,secretKey);
    }

    /**
     * 注入 存储器 管理实例
     * @return
     */
    @Bean
    public BucketManager bucketManager(){
        return new BucketManager(auth(),qiniuConfig());
    }

    /**
     * 注入Gson 实例  一重数据规范
     * @return
     */
    @Bean
    public Gson gson(){
        return new Gson();
    }

}
