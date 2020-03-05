package com.lhj.keepgym.manage.mapper;

import com.lhj.keepgym.bean.OrderCourse;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface OrderCourseMapper  extends Mapper<OrderCourse> {
    List<OrderCourse> findAllOrders();
}
