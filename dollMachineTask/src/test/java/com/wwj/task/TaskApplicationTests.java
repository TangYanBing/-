package com.wwj.task;

import com.wwj.task.beans.po.DemoUserPo;
import com.wwj.task.repository.DemoRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskApplicationTests {

    @Autowired
    DemoRepository demoRepository;
    @Test
    public void contextLoads() {
        DemoUserPo userInfo = demoRepository.findUserInfo("20003848");
        System.out.println(userInfo);
    }

}
