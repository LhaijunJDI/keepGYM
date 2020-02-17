package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Manager;
import com.lhj.keepgym.members.mapper.ManagerMapper;
import com.lhj.keepgym.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public Manager findManagerById(Integer id) {
        Manager manager = managerMapper.findManagerById(id);
        return manager;
    }
}
