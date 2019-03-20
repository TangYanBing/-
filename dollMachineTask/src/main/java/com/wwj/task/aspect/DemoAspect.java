package com.wwj.task.aspect;

import com.wwj.task.utils.ResultUtil;

import org.apache.ibatis.annotations.Arg;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * Function:
 *
 * @author TangYanBing
 * @date 21:43 2019/3/18
 * @since JDK 1.8
 */
@Slf4j
@Aspect
@Component
public class DemoAspect {

    /**
     * 切点
     */
    @Pointcut(value = "execution(public * com.wwj.task.controller.DemoController*.*(..)) " +
            "&& !execution(public * com.wwj.task.controller.DemoController.demoRedis(..))")
    public void a(){}



    @After(value = "execution(public * com.wwj.task.controller.DemoController*.*(..)) " +
            "&& !execution(public * com.wwj.task.controller.DemoController.demoRedis(..))")
    public void doVerify() {
        log.info("aaaaaaaaaaaaaa");
    }

}
