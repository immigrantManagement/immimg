package com.immimg.immimg.util;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @version v1.0
 * @ProjectName: springboot0110
 * @ClassName: RedisTemplateUtils
 * @Description: TODO(一句话描述该类的功能)
 * @Author: Nlqiong
 * @Date: 2020/1/11 16:57
 */
@Component
public class RedisTemplateUtils {

    /**
     * 声明Redis 可操作对象reidsTemplate
     */
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 对操作的数据进行序列化
     */
    private void serializeRedisData() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
    }

    /**
     * 创建存储 数据
     *
     * @param key
     * @param value
     * @return 返回是否存储成功
     */
    public boolean set(String key, String value) {
        try {
            serializeRedisData();
            ValueOperations<String, String> vo = redisTemplate.opsForValue();
            vo.set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 为数据设置时长
     *
     * @param key
     * @param seconds
     * @return
     */
    public boolean expire(final String key,final long seconds){
        return redisTemplate.execute(new RedisCallback<Boolean>(){

            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                boolean flag = false;
                try {
                    redisTemplate.setKeySerializer(new StringRedisSerializer());
                    redisTemplate.setValueSerializer(new StringRedisSerializer());
                    byte[] keys = new StringRedisSerializer().serialize(key);
                    flag = connection.expire(keys,seconds);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return flag;
            }
        });
    }


    /**
     * 存储 数据 并 设置有效时长
     *
     * @param key
     * @param value
     * @param expireTime 有效时间 以秒为单位
     * @return
     */
    public boolean set(String key, String value, long expireTime) {
        try {
            serializeRedisData();
            ValueOperations<String, String> vo = redisTemplate.opsForValue();
            vo.set(key, value);
            expire(key, expireTime);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通过key 获取 Value
     * @param key
     * @return 返回 value 值
     */
    public Object get(String key){
        serializeRedisData();
        ValueOperations<String, String> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

    /**
     * 查看 key 是否存在
     * @param key
     * @return
     */
    public boolean exists(String key){
        serializeRedisData();
        ValueOperations<String, String> vo = redisTemplate.opsForValue();
        String value = vo.get(key);
        if (value!=null && value!=""){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过key 删除数据
     * @param key
     * @return
     */
    public boolean delete(String key){
        serializeRedisData();
        if (exists(key)){
            redisTemplate.delete(key);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过key 获取有效时间
     * @param key
     * @return
     */
    public Long getExpire(String key){
        serializeRedisData();
        return redisTemplate.getExpire(key);
    }

    /**
     * 通过key修改 数据
     * @param key
     * @param value
     * @return
     */
    public boolean update(String key,String value){
        serializeRedisData();
        ValueOperations<String, String> vo = redisTemplate.opsForValue();
        Long expireTime = getExpire(key);
        if (expireTime == null){
            return false;
        } else if(expireTime == 0 || expireTime == -2){
            return false;
        }
        vo.set(key,value);
        expire(key,expireTime);
        return true;
    }


}
