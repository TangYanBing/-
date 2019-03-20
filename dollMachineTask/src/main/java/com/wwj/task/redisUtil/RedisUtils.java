package com.wwj.task.redisUtil;

import com.sun.xml.internal.ws.developer.Serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * Function:
 *
 * @author TangYanBing
 * @date 10:38 2019/3/18
 * @since JDK 1.8
 */
@Component
public class RedisUtils {
    private final
    StringRedisTemplate redisTemplate;

    @Autowired
    public RedisUtils(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * redis加锁
     * <p>
     * 原理： 1、利用redis的单线程模式控制多线程访问锁； 2、利用redis命令SETNX的特性，对于已存在的key值，再次插入不做修改；
     * 3、利用redis命令GETSET的特性，对于命令SETNX的value值进行获取老值设置新值操作在一个单线程操作； 适用场景： 用于商品的抢购、促销...
     * </p>
     *
     * @param key   key
     * @param value 当前时间+过期时间(时间戳)
     */
    public Boolean lock(String key, String value) {
        Boolean absent = redisTemplate.opsForValue().setIfAbsent(key, value);
        if (absent!=null&&absent) {
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //防止多线程
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            return !StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue);
        }
        return false;
    }

    /**
     * 解锁
     *
     * @param key   key
     * @param value value
     */
    public void unlock(String key, String value) {
        String currentValue = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
            redisTemplate.delete(key);
        }
    }
}
