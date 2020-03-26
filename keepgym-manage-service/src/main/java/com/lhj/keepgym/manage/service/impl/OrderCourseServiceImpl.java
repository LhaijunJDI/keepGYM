package com.lhj.keepgym.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Course;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.manage.mapper.CourseMapper;
import com.lhj.keepgym.manage.mapper.MembersMapper;
import com.lhj.keepgym.manage.mapper.OrderCourseMapper;
import com.lhj.keepgym.service.OrderCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Shinelon
 */
@Service(group = "manage")
public class OrderCourseServiceImpl implements OrderCourseService {

    @Autowired
    private OrderCourseMapper orderCourseMapper;

    @Autowired
    private MembersMapper membersMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    @Transactional
    public String saveOrderCourse(OrderCourse orderCourse) {

        OrderCourse orderCourse1 = orderCourseMapper.selectOrderCourseById(orderCourse.getCourseId(),orderCourse.getMemberId());
        if (orderCourse1 == null) {
            Course course = courseMapper.selectByPrimaryKey(orderCourse.getCourseId());
            if ("0".equals(course.getNum())) {
                return "full";
            }
            Members members = membersMapper.selectByPrimaryKey(orderCourse.getMemberId());
            if ("禁用".equals(members.getStatus())) {
                return "fail";
            }
            orderCourse.setOrderTime(new Date());
            orderCourse.setMemberName(members.getUsername());
            orderCourse.setStatus("0");
            int insert = orderCourseMapper.insert(orderCourse);
            if (insert > 0) {
                int num = Integer.parseInt(course.getNum()) - 1;
                course.setNum(String.valueOf(num));
                int i = courseMapper.updateByPrimaryKey(course);
                if (i > 0) {
                    return "success";
                }
            }
            return "fail";
        }
        return "already";
    }

    @Override
    public List<OrderCourse> findAllOrder() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        List<OrderCourse> allOrders = orderCourseMapper.findAllOrders();
        if (allOrders != null) {
            for (OrderCourse ordercourse : allOrders) {
                if ("0".equals(ordercourse.getStatus())) {
                    ordercourse.setStatus("未通知");
                }
                if ("1".equals(ordercourse.getStatus())) {
                    ordercourse.setStatus("已通知");
                }
                ordercourse.setStrOrderTime(simpleDateFormat.format(ordercourse.getOrderTime()));
            }
            return allOrders;
        }
        return null;
    }
}
