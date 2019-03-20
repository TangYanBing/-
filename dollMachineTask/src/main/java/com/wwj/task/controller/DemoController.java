package com.wwj.task.controller;

import com.wwj.task.beans.po.DemoUserPo;
import com.wwj.task.beans.vo.DemoVo;
import com.wwj.task.service.demo.DemoService;
import com.wwj.task.utils.Result;
import com.wwj.task.utils.ResultUtil;
import com.wwj.task.vlues.RedisKeyValues;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;

/**
 * Function:
 *
 * @author TangYanBing
 * @date 16:32 2019/3/11
 * @since JDK 1.8
 */
@Slf4j
@Validated
@RestController
@RequestMapping("demo")
public class DemoController {

    private final RedisTemplate<String, Serializable> redisTemplate;
    private final DemoService demoService;

    private final RestTemplate restTemplate;


    @Autowired
    public DemoController(RedisTemplate<String, Serializable> redisTemplate, DemoService demoService, RestTemplate restTemplate) {
        this.redisTemplate = redisTemplate;
        this.demoService = demoService;
        this.restTemplate = restTemplate;
    }

    /**
     * 预处理参数(一般用于from表单参数处理)
     * @param webDataBinder webDataBinder
     */
    @InitBinder("demoVo")
    public void initBinderDemoVo(WebDataBinder webDataBinder){
        webDataBinder.setFieldDefaultPrefix("demoVo.");
    }

    @RequestMapping("demoBean")
    public Result demoBean(@Valid DemoVo demoVo){
        log.info("demoVo={}",demoVo);
        return ResultUtil.success(demoVo);
    }

    @RequestMapping("demoParam")
    public Result demoParam(@NotNull String id){
        return ResultUtil.success(id);
    }


    @RequestMapping("demoMysql")
    public Result demoMysql(@Valid DemoVo demoVo){
        DemoUserPo userInfo = demoService.findUserInfo(demoVo.getId());
        userInfo.setName(new String(Base64.decodeBase64(userInfo.getName())));
        log.info("userInfo={}",userInfo);
        return ResultUtil.success(userInfo);
    }

    @RequestMapping("demoRedis")
    public Result demoRedis(@NotNull String userId,@NotNull String name){
        redisTemplate.boundValueOps(RedisKeyValues.USER_INFO+userId);
        redisTemplate.opsForValue().set(RedisKeyValues.USER_INFO+userId,name);
        String userName = (String)redisTemplate.opsForValue().get(RedisKeyValues.USER_INFO + userId);
        return ResultUtil.success(userName);
    }


}
