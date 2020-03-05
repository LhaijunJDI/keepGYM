package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.Manager;

import java.util.List;

public interface ManagerService {

    public Manager findManagerById(String id,String password);

    List<Manager> findAllManager();

    Manager findOneManagerById(String managerId);

    String updateManagerInfo(Manager manager);

    String deleteManagerById(String managerId);

    String insertManager(Manager manager);
}
