package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.members.mapper.OrderCourseMapper;
import com.lhj.keepgym.bean.Course;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.members.mapper.CourseMapper;
import com.lhj.keepgym.members.mapper.MembersMapper;
import com.lhj.keepgym.service.OrderCourseService;
import org.springframework.beans.factory.annotation.Autowired;

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
    public String saveOrderCourse(OrderCourse orderCourse) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date orderTime = new Date();
        simpleDateFormat.format(orderTime);
        String memberId = orderCourse.getMemberId();
        String courseId = orderCourse.getCourseId();
        if(courseId==null||memberId==null){
            return "fail";
        }
        //根据memberId查找用户
        Members members = membersMapper.selectByPrimaryKey(memberId);
        //根据课程id查找课程
        Course course = courseMapper.selectByPrimaryKey(courseId);
        //判断课程信息中的剩余人数是否为0，若为0则返回full表示人数已满
        if(course.getNum().equals("0")){
            return "full";
        }
        orderCourse.setMemberName(members.getUsername());
        orderCourse.setOrderTime(orderTime);
        //根据用户id和课程id查找预约信息
        OrderCourse orderCourse1 =orderCourseMapper.findOrderCourseById(memberId,courseId);
        //预约信息为空则说明该用户并未预约该课程
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
                //预约信息不为空则说明该用户已经预约过该课程
                return "already";
            }
        }

    @Override
    public List<OrderCourse> findAllOrder() {
        return null;
    }

}
