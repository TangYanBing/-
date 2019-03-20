package com.wwj.task.redisUtil;

import com.alibaba.fastjson.JSONObject;
import com.wwj.task.beans.po.DemoUserPo;
import com.wwj.task.vlues.RedisKeyValues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * Function:
 * 用于存储/获取用户信息缓存的类
 * @author TangYanBing
 * @date 18:04 2019/3/15
 * @since JDK 1.8
 */
@Component
public class RedisUserCache {

    private final
    RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    public RedisUserCache(RedisTemplate<String, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取信息
     * @param userId 用户id
     */
    public DemoUserPo getUser(String userId){

       return  (DemoUserPo) redisTemplate.opsForValue().get(userId);
    }

    /**
     * 获取信息
     * @param userPo 用户信息
     */
    public boolean setUser(DemoUserPo userPo){
        if(userPo!=null&&!StringUtils.isEmpty(userPo.getId())){
            redisTemplate.opsForValue().set(userPo.getId(), JSONObject.toJSONString(userPo));
            return true;
        }
        return false;
    }
}
