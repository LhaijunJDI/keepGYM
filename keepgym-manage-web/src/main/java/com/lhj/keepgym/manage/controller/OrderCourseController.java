package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.service.OrderCourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderCourseController {
    @Reference
    private OrderCourseService orderCourseService;

    @GetMapping("/toSearchAllOrderCourse")
    public List<OrderCourse> toSearchAllOrderCourse(){
        List<OrderCourse> orderCourses = orderCourseService.findAllOrder();
        return orderCourses;
    }
}
