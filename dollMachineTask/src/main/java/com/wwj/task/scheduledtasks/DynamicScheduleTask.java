package com.wwj.task.scheduledtasks;

import com.wwj.task.vlues.AttributesValues;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;

/**
 * Function:
 *  动态定时任务
 * @author TangYanBing
 * @date 14:06 2019/3/26
 * @since JDK 1.8
 */
@Slf4j
//@Component
@EnableScheduling
public class DynamicScheduleTask implements SchedulingConfigurer {

    /**
     * 可在数据库中查询coin
     */
    private static String coin = "0/5 * * * * ?";
    private static String coin_default = "0/5 * * * * ?";

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                () -> {
                    String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(AttributesValues.TIME_PATTERN_YYYY_MM_DD_HH_MM_SS_SSS));
                    log.info("执行动态定时任务: {}" , currentTime);
                },triggerContext -> {
                    if(StringUtils.isEmpty(coin)){
                        //不合法使用默认
                        coin = coin_default;
                    }
                    String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(AttributesValues.TIME_PATTERN_YYYY_MM_DD_HH_MM_SS_SSS));
                    log.info("更新定时任务计划: {}" , currentTime);
                    return new CronTrigger(coin).nextExecutionTime(triggerContext);
                }
        );
    }
}
