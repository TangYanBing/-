package com.wwj.task.iterceptor;

import com.alibaba.fastjson.JSON;
import com.wwj.task.vlues.AttributesValues;
import com.wwj.task.vlues.MathValues;
import com.wwj.task.vlues.StringValues;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * //@WebFilter(filterName="customFilter",urlPatterns={"/*"})
 * 可以使用servlet3.0自带注解@WebFilter
 * Function:
 * 过滤器
 * @author TangYanBing
 * @date 17:36 2019/3/14
 * @since JDK 1.8
 */
@Slf4j
@Component
public class DemoFilter implements Filter {

    private final static String REQUEST_ID = "requestId";

    /**
     * 过滤集合路径
     */
    private List<String> excludedUrls;

    @Override
    public  void init(FilterConfig filterConfig)  {
        String excludePattern = filterConfig.getInitParameter(AttributesValues.EXCLUSIONS);
        excludedUrls = Arrays.asList(excludePattern.split(StringValues.STRING_COMMA));
        log.info("初始化过滤器：{}",filterConfig.getFilterName());
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long startTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Map<String, String[]> parameterMap = httpRequest.getParameterMap();
        String requestId = getRequestId(httpRequest,parameterMap,startTime);
        MDC.put(REQUEST_ID, requestId);
        log.info("当前请求：{}，请求参数：{}", httpRequest.getContextPath() + httpRequest.getServletPath(), JSON.toJSONString(parameterMap));
        chain.doFilter(httpRequest,response);
        long endTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        log.info("请求响应时间：{}",(endTime-startTime));
        MDC.remove(REQUEST_ID);
    }

    private String getRequestId(HttpServletRequest request,Map<String, String[]> parameterMap,long requestTime) {
        String requestIdString= StringValues.STRING_EMPTY;
        String username = Optional.ofNullable(request.getHeader(AttributesValues.USER_ID)).orElse(StringValues.STRING_EMPTY);
        if(!StringUtils.isEmpty(username)){
            requestIdString=username+StringValues.STRING_HORIZONTAL;
        }
        String[] requestId = parameterMap.get(REQUEST_ID);
        if(requestId!=null){
            requestIdString =requestIdString+requestId[MathValues.INTEGER0]+StringValues.STRING_HORIZONTAL;
        }

        String uuid = UUID.randomUUID().toString().replaceAll(StringValues.STRING_HORIZONTAL,StringValues.STRING_EMPTY).substring(MathValues.INTEGER0,MathValues.INTEGER6);
        requestIdString=requestIdString+requestTime+StringValues.STRING_HORIZONTAL+uuid;
        return requestIdString;
    }

    @Override
    public void destroy() {
        log.info("销毁过滤器");
    }
}
