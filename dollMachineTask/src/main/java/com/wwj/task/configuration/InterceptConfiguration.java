package com.wwj.task.configuration;

import com.wwj.task.iterceptor.LoggerIntercept;
import com.wwj.task.vlues.AttributesValues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Function:
 * 拦截器配置
 * @author TangYanBing
 * @date 16:38 2019/3/11
 * @since JDK 1.8
 */
@Configuration
public class InterceptConfiguration implements WebMvcConfigurer {

    private final
    LoggerIntercept loggerIntercept;

    @Autowired
    public InterceptConfiguration(LoggerIntercept loggerIntercept) {
        this.loggerIntercept = loggerIntercept;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loggerIntercept)
                .addPathPatterns(AttributesValues.ALL_URL_PATTERN_1);
    }
}
