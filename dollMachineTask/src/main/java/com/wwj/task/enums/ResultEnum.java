package com.wwj.task.enums;

import com.wwj.task.vlues.MathValues;
import com.wwj.task.vlues.StringValues;

import lombok.Getter;

/**
 * Function:
 *
 * @author TangYanBing
 * @date 15:41 2019/3/12
 * @since JDK 1.8
 */
public enum ResultEnum {
    /**成功状态*/
    SUCCESS(MathValues.INTEGER0, StringValues.STRING_EMPTY),
    /** 参数验证*/
    PARAM_ERROR(MathValues.INTEGER1, StringValues.PARAMETER_ERROR),
    /**业务代码错误*/
    CODE_ERROR(MathValues.INTEGER500, StringValues.CODE_ERROR),
    /**用户不存在*/
    USER_INVALID(MathValues.INTEGER2, StringValues.USER_INVALID)
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }}
