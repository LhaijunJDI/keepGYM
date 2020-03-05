package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.bean.Coach;
import com.lhj.keepgym.service.CoachService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoachController {

    @Reference
    private CoachService coachService;

    @GetMapping("/toSearchAllCoach")
    public List<Coach> toSearchAllCoach(){
        List<Coach> Coachs = coachService.findAllCoach();
        return Coachs;
    }


    @GetMapping("/toSearchCoachInfo")
    public Coach toSearchCoachInfo(String coachId){
        Coach Coach = coachService.findCoachById(coachId);
        return Coach;
    }


    @PostMapping("/toAlterCoachInfo")
    public String toAlterCoachInfo(@RequestBody Coach coach){
        String result = coachService.updateCoach(coach);
        return result;
    }



    @DeleteMapping("/toDeleteCoach")
    public String toDeleteCoach(String coachId){
        String result = coachService.deleteCoach(coachId);
        return result;
    }

    @PutMapping("/toAddCoach")
    public String toAddCoach(@RequestBody Coach coach){
        String result = coachService.insertCoach(coach);
        return result;
    }
}
