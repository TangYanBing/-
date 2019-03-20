package com.wwj.task.configuration;

import com.wwj.task.iterceptor.DemoFilter;
import com.wwj.task.vlues.AttributesValues;
import com.wwj.task.vlues.MathValues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Function:
 * 过滤器注册中心
 * @author TangYanBing
 * @date 17:30 2019/3/14
 * @since JDK 1.8
 */
@Configuration
public class FilterConfiguration {

    private final
    DemoFilter demoFilter;

    @Autowired
    public FilterConfiguration(DemoFilter demoFilter) {
        this.demoFilter = demoFilter;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean<DemoFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(demoFilter);
        String logCostFilter = "LogCostFilter";
        filterRegistrationBean.setName(logCostFilter);
        filterRegistrationBean.setOrder(MathValues.INTEGER0);
        filterRegistrationBean.addUrlPatterns(AttributesValues.ALL_URL_PATTERN);
        filterRegistrationBean.addInitParameter(AttributesValues.EXCLUSIONS,AttributesValues.EXCLUSIONS_VALUES );
        return filterRegistrationBean;
    }
}
