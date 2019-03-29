package com.wwj.task.service.impl.demo;

import com.wwj.task.beans.po.DemoUserPo;
import com.wwj.task.repository.DemoRepository;
import com.wwj.task.service.demo.DemoService;
import com.wwj.task.vlues.MathValues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import lombok.extern.slf4j.Slf4j;

/**
 * Function:
 *
 * @author TangYanBing
 * @date 09:37 2019/3/14
 * @since JDK 1.8
 */
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    private final
    DemoRepository demoRepository;

    @Autowired
    public DemoServiceImpl(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }


    @Override
    public DemoUserPo findUserInfo(String id) {
        return demoRepository.findUserInfo(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer appealRecordBackUp(){
        LocalDateTime now = LocalDateTime.now();
        long thisYearStartSecond = now.withDayOfYear(1).withHour(0).withMinute(0).withSecond(0).toEpochSecond(ZoneOffset.of("+8"));
        int appealRecordBackUpCount = demoRepository.findAppealRecordBackUpCount(thisYearStartSecond);
        if(appealRecordBackUpCount>0){
            int year = now.minusYears(1).getYear();
            //备份库
            String databaseName = "doll_live";
            //备份表名称
            String tableName = "appeal_record_"+year;
            //需要备份
            int appealRecordBackMaxId = demoRepository.findAppealRecordBackUpMaxId(thisYearStartSecond);
            //备份成功条数
            int backUpCount;
            //判断备份表是否存在
            int tableExists = demoRepository.findTableCountByTableName(tableName, databaseName);
            if(tableExists== MathValues.INTEGER0){
                //创建新表
                demoRepository.createAppealRecordBackUpTable(year);
            }
            //复制数据到该表
            backUpCount = demoRepository.insertAppealRecordBackUp(appealRecordBackMaxId, year);
            //验证数据备份
            if(backUpCount<=0||backUpCount!=appealRecordBackUpCount){
                log.info("【申诉记录备份】失败backUpCount：{}，appealRecordBackUpCount：{}",backUpCount,appealRecordBackUpCount);
                throw new RuntimeException();
            }
            int deleteNumber = demoRepository.clearAppealRecordById(appealRecordBackMaxId);
            if(backUpCount!=deleteNumber){
                log.info("【申诉记录备份】失败 backUpCount：{}，deleteNumber：{}",backUpCount,deleteNumber);
                throw new RuntimeException();
            }
            log.info("【申诉记录备份】成功 appealRecordBackUpCount={}",appealRecordBackUpCount);
        }
        return appealRecordBackUpCount;
    }
}
