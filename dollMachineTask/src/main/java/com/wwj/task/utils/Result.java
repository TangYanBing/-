package com.wwj.task.utils;

import com.wwj.task.enums.ResultEnum;

import lombok.Data;

/**
 * Function:
 *
 * @author TangYanBing
 * @date 15:11 2019/3/12
 * @since JDK 1.8
 */
@Data
public class Result<T> {

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String errorMessage;
    /**
     * 具体内容
     */
    private T data;

    Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.errorMessage = resultEnum.getMessage();
    }

    Result(Integer code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }
}
