package com.lhj.keepgym.members.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.Coach;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.service.CoachService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class CoachController {

    @Reference(group = "member")
    private CoachService coachService;

    //前往教练信息页面
    @RequestMapping("/toMemberCoach")
    @LoginRequired
    public String toMemberCoach() {
        return "memberCoach";
    }

    //查询全部教练信息
    @LoginRequired
    @RequestMapping("/toSearchAllCoach")
    @ResponseBody
    public List<Coach> toSearchAllCoach(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Coach> allCoach = coachService.findAllCoach();
        for (Coach coach1:allCoach){
            String enterTime = simpleDateFormat.format(coach1.getEnterTime());
           coach1.setStrEnterTime(enterTime);
        }
        return allCoach;
    }


}
