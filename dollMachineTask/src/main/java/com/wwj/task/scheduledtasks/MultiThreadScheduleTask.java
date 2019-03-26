package com.wwj.task.scheduledtasks;

import com.wwj.task.vlues.AttributesValues;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;

/**
 * Function:
 * 多线程定时任务
 * @author TangYanBing
 * @date 14:34 2019/3/26
 * @since JDK 1.8
 */
@Slf4j
@EnableAsync
@EnableScheduling
//@Component
public class MultiThreadScheduleTask {


    @Async
    @Scheduled(fixedDelay = 1000L)
    void task1(){
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(AttributesValues.TIME_PATTERN_YYYY_MM_DD_HH_MM_SS_SSS));
        log.info("第一个定时任务开始 : {}\r\n线程 : {}" ,currentTime, Thread.currentThread().getName());
    }

    @Async
    @Scheduled(fixedDelay = 1000L)
    void task2(){
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(AttributesValues.TIME_PATTERN_YYYY_MM_DD_HH_MM_SS_SSS));
        log.info("第二个定时任务开始 : {}\r\n线程 : {}" ,currentTime, Thread.currentThread().getName());
    }

}
