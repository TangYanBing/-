package com.wwj.task.enums;

import com.wwj.task.vlues.MathValues;
import com.wwj.task.vlues.StringValues;

import lombok.Getter;

/**
 * Function:
 *
 * @author TangYanBing
 * @date 16:16 2019/3/12
 * @since JDK 1.8
 */
@Getter
public enum  PayStatusEnum implements CodeEnum {
    /**等待支付*/
    WAIT(MathValues.INTEGER1, StringValues.WAIT_PAY),
    /**支付成功*/
    SUCCESS(MathValues.INTEGER2, StringValues.PAY_SUCCESS)
            ;
    /**状态码*/
    private Integer code;
    /**状态*/
    private String status;

    PayStatusEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }}
