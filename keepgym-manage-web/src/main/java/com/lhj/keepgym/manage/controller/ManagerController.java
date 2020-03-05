package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.bean.Manager;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.service.ClockService;
import com.lhj.keepgym.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManagerController {
    @Reference
    private ManagerService managerService;

    @Reference
    private ClockService clockService;

    @GetMapping("/checkManage")
    @ResponseBody
    public String getManagerById(String id, String password) {
        Manager result = managerService.findManagerById(id, password);
        if (result == null) {
            return "fail";
        }
        return "success";
    }

    @RequestMapping("/toManageIndex")
    public String toManageIndex(String managerId, Model model) {
        model.addAttribute("managerId", managerId);
        return "manageIndex";
    }

    @RequestMapping("/toMemberManage")
    public String toMemberManage(String managerId, Model model) {
        model.addAttribute("managerId", managerId);
        return "memberManage";
    }

    @RequestMapping("/toEmployeeManage")
    public String toEmployeeManage(String managerId, Model model) {
        model.addAttribute("managerId", managerId);
        return "employeeManage";
    }

    @RequestMapping("/toCourseManage")
    public String toCourseManage(String managerId, Model model) {
        model.addAttribute("managerId", managerId);
        return "courseManage";
    }

    @RequestMapping("/toNoticeManage")
    public String toNoticeManage(String managerId, Model model) {
        model.addAttribute("managerId", managerId);
        return "noticeManage";
    }


    @GetMapping("/toSearchAllManager")
    @ResponseBody
    public List<Manager> toSearchAllManager() {
        List<Manager> managers = managerService.findAllManager();
        return managers;
    }


    @GetMapping("/toSearchManagerInfo")
    @ResponseBody
    public Manager toSearchManagerInfo(String managerId) {
        Manager managers = managerService.findOneManagerById(managerId);
        return managers;
    }

    @PostMapping("/toAlterManagerInfo")
    @ResponseBody
    public String toAlterManagerInfo(@RequestBody Manager manager) {
        String result = managerService.updateManagerInfo(manager);
        return result;
    }


    @DeleteMapping("/toDeleteManager")
    @ResponseBody
    public String toDeleteManager(String managerId) {
        String result = managerService.deleteManagerById(managerId);
        return result;
    }


    @DeleteMapping("/toAddManager")
    @ResponseBody
    public String toAddManager(@RequestBody Manager manager) {
        String result = managerService.insertManager(manager);
        return result;
    }
}
