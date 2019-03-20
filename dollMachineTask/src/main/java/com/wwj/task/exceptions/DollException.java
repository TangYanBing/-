package com.wwj.task.exceptions;

import com.wwj.task.enums.ResultEnum;

import lombok.Getter;


/**
 * Function:
 *
 * @author TangYanBing
 * @date 16:32 2019/3/12
 * @since JDK 1.8
 */
@Getter
public class DollException extends RuntimeException {

    private Integer code;

    public DollException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public DollException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
