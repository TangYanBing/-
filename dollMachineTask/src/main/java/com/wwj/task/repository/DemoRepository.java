package com.wwj.task.repository;

import com.wwj.task.beans.po.DemoUserPo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 判断表是否存在
     * @param tableName 表名
     * @param databaseName 数据库名
     * @return true/false
     */
    int findTableCountByTableName(@Param("tableName") String tableName, @Param("databaseName")String databaseName);


    /**
     * 查询本次操作数据条数
     * @param dateTime 小于该时间的数据
     * @return 数据条数
     */
    int findAppealRecordBackUpCount(@Param("dateTime") Long dateTime);

    /**
     * 查询本次操作数据最大id（索引主键）
     * @param dateTime 小于该时间的数据
     * @return 数据条数
     */
    int findAppealRecordBackUpMaxId(@Param("dateTime") Long dateTime);

    /**
     * 对表进行数据备份 并且备份表不存在
     * @param id 小于等于该id的数据
     * @param year 备份年表
     * @return 数据条数
     */
    int createAppealRecordBackUpTable(@Param("year")Integer year);


    /**
     * 对存在的表进行数据备份
     * @param id 小于等于该id的数据
     * @param year 备份年表
     * @return 数据条数
     */
    int insertAppealRecordBackUp(@Param("id") Integer id,@Param("year") Integer year);

    /**
     * 清除已经备份的数据
     * @param id 小于等于该id的数据
     * @return 数据条数
     */
    int clearAppealRecordById(@Param("id") Integer id);
}
