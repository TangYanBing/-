package com.wwj.task.repository;

import com.wwj.task.beans.po.DemoUserPo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Function:
 *
 * @author TangYanBing
 * @date 20:28 2019/3/13
 * @since JDK 1.8
 */
@Repository
public interface DemoRepository {

    /**
     * 查询用户信息
     * @param id id
     * @return DemoUserPo
     */
    DemoUserPo findUserInfo(@Param(value = "id") String id);
}
