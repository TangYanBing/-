package com.wwj.task.service.impl.demo;

import com.wwj.task.beans.po.DemoUserPo;
import com.wwj.task.repository.DemoRepository;
import com.wwj.task.service.demo.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Function:
 *
 * @author TangYanBing
 * @date 09:37 2019/3/14
 * @since JDK 1.8
 */
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
}
