package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.Course;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.service.OrderCourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Shinelon
 */
@RestController
public class OrderCourseController {
    @Reference(group = "manage")
    private OrderCourseService orderCourseService;


    /**
     * 添加课程预约信息
     * @param courseId
     * @param memberId
     * @return
     */
    @PutMapping("/toOrderCourse")
    public String toOrderCourse(String courseId, String memberId) {
        OrderCourse orderCourse = new OrderCourse();
        orderCourse.setCourseId(courseId);
        orderCourse.setMemberId(memberId);
        return orderCourseService.saveOrderCourse(orderCourse);
    }

    /**
     * 查询课程预约信息
     * @return
     */
    @GetMapping("/toSearchAllOrderCourse")
    public List<OrderCourse> toSearchAllOrderCourse() {
        return orderCourseService.findAllOrder();
    }
}
