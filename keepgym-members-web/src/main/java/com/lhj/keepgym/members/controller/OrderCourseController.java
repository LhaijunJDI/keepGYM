package com.lhj.keepgym.members.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.service.OrderCourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Shinelon
 */
@Controller
public class OrderCourseController {

    @Reference(group = "member")
    private OrderCourseService orderCourseService;

    /**
     * 预约课程
     * @param orderCourse
     * @return
     */
    @LoginRequired
    @PostMapping("/saveOrderCourse")
    @ResponseBody
    public String saveOrderCourse(@RequestBody OrderCourse orderCourse){
        String result = orderCourseService.saveOrderCourse(orderCourse);
        return result;
    }
}
