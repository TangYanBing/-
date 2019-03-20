package com.wwj.task.beans.vo;


import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * Function:
 *
 * @author TangYanBing
 * @date 16:13 2019/3/13
 * @since JDK 1.8
 */
@Data
public class DemoVo {

    @NotEmpty()
    private String id;
    @NotEmpty()
    private String name;
    @NotEmpty()
    private String password;
}
