package com.wwj.task.utils;

import com.wwj.task.enums.CodeEnum;

/**
 * Function:
 * 枚举工具类
 * @author TangYanBing
 * @date 16:13 2019/3/12
 * @since JDK 1.8
 */
public class EnumUtil {

    public static  <T extends CodeEnum> T getByCode(Integer code, Class<T> codeClass){
            for (T e:codeClass.getEnumConstants()){
                if(code.equals(e.getCode())){
                    return e;
                }
            }
            return null;
    }
}
