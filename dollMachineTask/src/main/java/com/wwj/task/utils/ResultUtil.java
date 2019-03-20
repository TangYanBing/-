package com.wwj.task.utils;

import com.wwj.task.enums.ResultEnum;
import com.wwj.task.exceptions.DollException;
import com.wwj.task.vlues.StringValues;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Function:
 *
 * @author TangYanBing
 * @date 15:13 2019/3/12
 * @since JDK 1.8
 */
public class ResultUtil {

    /**
     * 成功返回结果
     * @param data 返回结果
     */
    public static Result success(Object data){
        Result<Object> result = new Result<>(ResultEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 返回空的正确
     */
    public static Result success(){
        return success(null);
    }

    /**
     * 错误返回
     * @param resultEnum 返回码
     */
    public static Result error(ResultEnum resultEnum){
        return new Result<>(resultEnum);
    }

    /**
     * 错误返回
     * @param code 错误码
     * @param message 错误提示
     */
    public static Result error(Integer code,String message){
        return new Result<>(code,message);
    }

}
