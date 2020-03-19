package com.lhj.keepgym.members.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.OrderCoach;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.service.OrderCoachService;
import com.lhj.keepgym.service.OrderCourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderCoachController {

    @Reference
    private OrderCoachService orderCoachService;

    //预约课程
    @LoginRequired
    @PutMapping("/saveOrderCoach")
    public String saveOrderCoach(@RequestBody OrderCoach OrderCoach){
        String result = orderCoachService.saveOrderCoach(OrderCoach);
        return result;
    }
}
