package com.immimg.immimg.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @version v1.0
 * @ProjectName: springboot0110
 * @ClassName: QNUtils
 * @Description: TODO(一句话描述该类的功能)
 * @Author: Nlqiong
 * @Date: 2020/1/14 8:08
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "qiniuyun")
public class QNUtils implements InitializingBean {
    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private BucketManager bucketManager;
    @Autowired
    private Auth auth;

    private String bucket;
    private String path;
    private StringMap putPolicy;

    private Response uploadFile(InputStream inputStream) {
        Response response = null;
        try {
            response = uploadManager.put(inputStream, null, getToken(), null, null);
            int trytimes = 0;
            while (response.needRetry() && trytimes < 3) {
                response = uploadManager.put(inputStream, null, getToken(), null, null);
                trytimes++;
            }
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
    }

    private String getToken() {
        return this.auth.uploadToken(bucket, null, 3600, putPolicy);
    }


    public String getUrl(MultipartFile file){
        Response response = null;
        try {
            response = uploadFile(file.getInputStream());
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String url = path+"/"+putRet.key;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
