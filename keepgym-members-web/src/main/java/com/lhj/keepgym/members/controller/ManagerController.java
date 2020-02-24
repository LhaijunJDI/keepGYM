package com.lhj.keepgym.members.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.bean.Manager;
import com.lhj.keepgym.service.ManagerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {
    @Reference
    private ManagerService managerService;

    @GetMapping("/getManagerById")
    public String getManagerById(Integer id,String password){
        Manager manager = managerService.findManagerById(id);
        String pwd = manager.getPassword();
        if(pwd!=null) {
            if (password.equals(pwd)) {
                return "OK";
            }else{
                return "False";
            }
        }
            return "noManager";

    }
}
