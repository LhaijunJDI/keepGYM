package com.lhj.keepgym.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Manager;
import com.lhj.keepgym.manage.mapper.ManagerMapper;
import com.lhj.keepgym.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Shinelon
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;


    @Override
    public Manager findManagerById(String id, String password) {
        Manager manager = new Manager();
        manager.setId(id);
        manager.setPassword(password);
        manager = managerMapper.selectOne(manager);
        if (manager == null) {
            return null;
        }
        return manager;
    }

    @Override
    public List<Manager> findAllManager() {
        List<Manager> managers = managerMapper.selectAll();
        if (managers != null) {
            for (Manager manager : managers) {
                if ("1".equals(manager.getGender())) {

                    manager.setGender("男");
                } else {
                    manager.setGender("女");
                }
            }
            return managers;
        }
        return null;
    }

    @Override
    public Manager findOneManagerById(String managerId) {
        Manager manager = managerMapper.selectByPrimaryKey(managerId);
        if (manager != null) {
            return manager;
        }
        return null;
    }

    @Override
    public String updateManagerInfo(Manager manager) {
        int i = managerMapper.updateByPrimaryKeySelective(manager);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @Override
    public String deleteManagerById(String managerId) {
        int i = managerMapper.deleteByPrimaryKey(managerId);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @Override
    public String insertManager(Manager manager) {
        manager.setPassword("123456");
        int i = managerMapper.insertSelective(manager);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }
}
