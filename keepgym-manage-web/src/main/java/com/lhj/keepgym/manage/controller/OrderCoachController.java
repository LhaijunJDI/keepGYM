package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.OrderCoach;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.service.OrderCoachService;
import com.lhj.keepgym.service.OrderCourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shinelon
 */
@RestController
public class OrderCoachController {
    @Reference
    private OrderCoachService orderCoachService;


    /**
     * 购买私教
     * @param coachId
     * @param memberId
     * @param num
     * @param managerId
     * @return
     */
    @PutMapping("/toBuyCoachClass")
    public String toBuyCoachClass(String coachId,String memberId,String num,String managerId){
        OrderCoach orderCoach = new OrderCoach();
        orderCoach.setCoachId(coachId);
        orderCoach.setMemberId(memberId);
        orderCoach.setNum(num);
        return orderCoachService.buyCoachClass(orderCoach,managerId);
    }

    /**
     * 查询所有预约私教信息
     * @return
     */
    @GetMapping("/toSearchAllOrderCoach")
    public List<OrderCoach> toSearchAllOrderCoach(){
        return orderCoachService.findAllOrder();
    }


    /**
     * 更新剩余私教课数
     * @param coachId
     * @param memberId
     * @return
     */
    @PostMapping("/toUpdateOrderCoachNum")
    public String toUpdateOrderCoachNum(String coachId,String memberId){
        return orderCoachService.updateOrderCoachNum(coachId,memberId);
    }
}
