package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Course;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.members.mapper.CourseMapper;
import com.lhj.keepgym.members.mapper.MembersMapper;
import com.lhj.keepgym.members.mapper.OrderCourseMapper;
import com.lhj.keepgym.service.OrderCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderCourseServiceImpl implements OrderCourseService {

    @Autowired
    private OrderCourseMapper orderCourseMapper;

    @Autowired
    private MembersMapper membersMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public OrderCourse queryById(String courseId) {
        return null;
    }

    @Override
    public List<OrderCourse> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public OrderCourse insert(OrderCourse orderCourse) {
        return null;
    }

    @Override
    public OrderCourse update(OrderCourse orderCourse) {
        return null;
    }

    @Override
    public boolean deleteById(String courseId) {
        return false;
    }

    @Override
    public String saveOrderCourse(OrderCourse orderCourse) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date orderTime = new Date();
        simpleDateFormat.format(orderTime);
        String memberId = orderCourse.getMemberId();
        String courseId = orderCourse.getCourseId();
        if(courseId==null||memberId==null){
            return "fail";
        }
        Members members = membersMapper.selectByPrimaryKey(memberId);
        Course course = courseMapper.selectByPrimaryKey(courseId);
        if(course.getNum().equals("0")){
            return "full";
        }
        orderCourse.setMemberName(members.getUsername());
        orderCourse.setOrderTime(orderTime);
        OrderCourse orderCourse1 =orderCourseMapper.findOrderCourseById(memberId,courseId);
            if (orderCourse1 == null){
                int result = orderCourseMapper.insertSelective(orderCourse);
                if(result>0){
                    int i = orderCourseMapper.updateCourseNum(courseId);
                    if(i>0) {
                        return "success";
                    }
                }
                return "fail";
            } else {
                return "already";
            }
        }

}
