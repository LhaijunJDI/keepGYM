package com.lhj.keepgym.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.manage.mapper.OrderCourseMapper;
import com.lhj.keepgym.service.OrderCourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class OrderCourseServiceImpl implements OrderCourseService {

    @Autowired
    private OrderCourseMapper orderCourseMapper;
    @Override
    public String saveOrderCourse(OrderCourse orderCourse) {
        return null;
    }

    @Override
    public List<OrderCourse> findAllOrder() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        List<OrderCourse> allOrders = orderCourseMapper.findAllOrders();
        if(allOrders!=null){
            for(OrderCourse ordercourse:allOrders){
                if("0".equals(ordercourse.getStatus())){
                    ordercourse.setStatus("未通知");
                }
                if("1".equals(ordercourse.getStatus())){
                    ordercourse.setStatus("已通知");
                }
                ordercourse.setStrOrderTime(simpleDateFormat.format(ordercourse.getOrderTime()));
            }
            return allOrders;
        }
        return null;
    }
}
