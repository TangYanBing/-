package com.wwj.task.service.demo;

import com.wwj.task.beans.po.DemoUserPo;

import org.apache.ibatis.annotations.Param;

/**
 * Function:
 *
 * @author TangYanBing
 * @date 09:34 2019/3/14
 * @since JDK 1.8
 */
public interface DemoService {

    /**
     * demo查询
     * @param id id
     * @return DemoUser信息
     */
    DemoUserPo findUserInfo(String id);


    Integer appealRecordBackUp();
}
