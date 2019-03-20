package com.wwj.task.exceptions;

import com.wwj.task.enums.ResultEnum;
import com.wwj.task.utils.Result;
import com.wwj.task.utils.ResultUtil;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;

/**
 * Function:
 * 异常处理
 * @author TangYanBing
 * @date 16:41 2019/3/12
 * @since JDK 1.8
 */
@Slf4j
@RestControllerAdvice
public class DollExceptionHandler {

    /**
     *  DollException异常处理
     */
    @ExceptionHandler(value = DollException.class)
    public Result exceptionLog(DollException e){
        return ResultUtil.error(e.getCode(),e.getMessage());
    }

    /**
     *  验证器：javax.validation
     *  参数验证器异常处理
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result exceptionLog(ConstraintViolationException e){
        return ResultUtil.error(ResultEnum.PARAM_ERROR.getCode(),e.getMessage());
    }

    /**
     * 全部异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public Result exceptionLog(Exception e){
        log.error("",e);
        return ResultUtil.error(ResultEnum.CODE_ERROR);
    }
}
