package com.wwj.task.iterceptor;

import com.alibaba.fastjson.JSON;
import com.wwj.task.vlues.AttributesValues;
import com.wwj.task.vlues.MathValues;
import com.wwj.task.vlues.StringValues;

import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;


/**
 * Function:
 * 拦截器
 * @author TangYanBing
 * @date 16:06 2019/3/11
 * @since JDK 1.8
 */
@Slf4j
@Component
public class LoggerIntercept implements HandlerInterceptor  {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 @Nullable Exception ex)  {

    }


}
