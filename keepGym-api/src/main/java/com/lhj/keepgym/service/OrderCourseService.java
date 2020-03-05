package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.OrderCourse;
import java.util.List;

/**
 * (OrderCourse)表服务接口
 *
 * @author makejava
 * @since 2020-02-23 21:37:15
 */
public interface OrderCourseService {



    String saveOrderCourse(OrderCourse orderCourse);

    List<OrderCourse> findAllOrder();
}