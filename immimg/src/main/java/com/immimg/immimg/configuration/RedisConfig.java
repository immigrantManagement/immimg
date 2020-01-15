package com.immimg.immimg.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @version v1.0
 * @ProjectName: springboot0110
 * @ClassName: RedisConfig
 * @Description: TODO(一句话描述该类的功能)
 * @Author: Nlqiong
 * @Date: 2020/1/10 15:23
 */
@Configuration
@EnableCaching
public class RedisConfig{
    //向spring 中注入 redis 可操作对象redisTemplate
    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
}
