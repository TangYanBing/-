package com.wwj.task.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Function:
 * 时间工具类
 * @author TangYanBing
 * @date 14:47 2019/3/18
 * @since JDK 1.8
 */
public class DateUtils {
    
    private final static String OFFSET_ID = "+8";
    

    /**
     * 得到当前时间的秒数
     */
    public static long nowToSeconds() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of(OFFSET_ID));
    }

    /**
     * 得到当前时间的毫秒数
     */
    public static long nowToMillisecond() {
        return LocalDateTime.now().toInstant(ZoneOffset.of(OFFSET_ID)).toEpochMilli();
    }

    /**
     * LocalDateTime 时间格式转换
     */
    public static String dateConversion(LocalDateTime dateTime,String formatter) {

        return dateTime.format(DateTimeFormatter.ofPattern(formatter));
    }

    /**
     * LocalDate 时间格式转换
     */
    public static String dateConversion(LocalDate dateTime, String formatter) {

        return dateTime.format(DateTimeFormatter.ofPattern(formatter));
    }

}
