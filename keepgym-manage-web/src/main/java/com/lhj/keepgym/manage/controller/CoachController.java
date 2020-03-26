package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.Coach;
import com.lhj.keepgym.service.CoachService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shinelon
 */
@RestController
public class CoachController {

    @Reference(group = "manage")
    private CoachService coachService;

    /**
     *
     * @return 查询全部的教练
     */
    @GetMapping("/toSearchAllCoach")
    public List<Coach> toSearchAllCoach(){
        return coachService.findAllCoach();
    }

    /**
     *
     * @param coachId
     * @return 查询教练信息
     */
    @GetMapping("/toSearchCoachInfo")
    public Coach toSearchCoachInfo(String coachId){
        return coachService.findCoachById(coachId);
    }

    /**
     * 修改教练信息
     * @param coach
     * @return
     */
    @PostMapping("/toAlterCoachInfo")
    public String toAlterCoachInfo(@RequestBody Coach coach){
        return coachService.updateCoach(coach);
    }

    /**
     * 删除教练
     * @param coachId
     * @return
     */
    @DeleteMapping("/toDeleteCoach")
    public String toDeleteCoach(String coachId){
        return coachService.deleteCoach(coachId);
    }

    /**
     * 添加教练
     * @param coach
     * @return
     */
    @PutMapping("/toAddCoach")
    public String toAddCoach(@RequestBody Coach coach){
        return coachService.insertCoach(coach);
    }
}
