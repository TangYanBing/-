package com.wwj.task.scheduledtasks;

import com.wwj.task.vlues.AttributesValues;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;

/**
 * Function:
 *  定时任务
 * @author TangYanBing
 * @date 13:49 2019/3/26
 * @since JDK 1.8
 */
@Slf4j
@Component
@EnableScheduling
public class StaticScheduleTask {

    @Scheduled(cron = "0/2 * * * * ?")
    private void task1(){
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(AttributesValues.TIME_PATTERN_YYYY_MM_DD_HH_MM_SS_SSS));
        log.info("第一个StaticScheduleTask定时任务：运行时间={}，\r\n线程 : {}",currentTime,Thread.currentThread().getName());
    }

    @Scheduled(cron = "0/2 * * * * ?")
    private void task2(){
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(AttributesValues.TIME_PATTERN_YYYY_MM_DD_HH_MM_SS_SSS));
        log.info("第二个StaticScheduleTask定时任务：运行时间={}，\r\n线程 : {}",currentTime,Thread.currentThread().getName());
    }
}
