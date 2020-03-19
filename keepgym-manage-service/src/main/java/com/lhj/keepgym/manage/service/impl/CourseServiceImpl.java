package com.lhj.keepgym.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Coach;
import com.lhj.keepgym.bean.Course;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.manage.mapper.CoachMapper;
import com.lhj.keepgym.manage.mapper.CourseMapper;
import com.lhj.keepgym.manage.mapper.OrderCourseMapper;
import com.lhj.keepgym.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CoachMapper coachMapper;

    @Autowired
    private OrderCourseMapper orderCourseMapper;

    @Override
    public List<Course> findAllCourse() {
        Coach coach =new Coach();
        List<Course> courses = courseMapper.selectAll();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd hh:mm");
        if (courses != null) {
            for(Course course:courses){
                coach = coachMapper.selectByPrimaryKey(course.getCourseId());
                course.setCoachName(coach.getName());
                course.setStrStartTime(simpleDateFormat.format(course.getStartTime()));
                course.setStrEndTime(simpleDateFormat.format(course.getEndTime()));
            }
            return courses;
        }
        return null;
    }

    @Override
    public Course findCourseById(String courseId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Course course = courseMapper.selectByPrimaryKey(courseId);
        if (course != null) {
            course.setStrStartTime(simpleDateFormat.format(course.getStartTime()));
            course.setStrEndTime(simpleDateFormat.format(course.getEndTime()));
            return course;
        }
        return null;
    }

    @Override
    public String updateCourseInfo(Course course) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        course.setStartTime(simpleDateFormat.parse(course.getStrStartTime()));
        course.setEndTime(simpleDateFormat.parse(course.getStrEndTime()));
        int i = courseMapper.updateByPrimaryKeySelective(course);
        if(i>0){
            return "success";
        }
        return "fail";
    }

    @Override
    public String deleteCourse(String courseId) {
        OrderCourse orderCourse = new OrderCourse();
        Example example = new Example(OrderCourse.class);
        example.createCriteria().andEqualTo("courseId",courseId);
        List<OrderCourse> orderCourses = orderCourseMapper.selectByExample(example);
        if(orderCourses!=null){
            Iterator<OrderCourse> iterator = orderCourses.iterator();
            while (iterator.hasNext()){
                orderCourse = iterator.next();
                int i = orderCourseMapper.delete(orderCourse);
                if(i<=0){
                    return "fail";
                }
            }
            int i = courseMapper.deleteByPrimaryKey(courseId);
            if(i>0){
                return "success";
            }
        }
        return "fail";
    }

    @Override
    public String insertCourse(Course course) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        course.setStartTime(simpleDateFormat.parse(course.getStrStartTime()));
        course.setEndTime(simpleDateFormat.parse(course.getStrEndTime()));
        course.setCreateTime(new Date());
        int i = courseMapper.insertSelective(course);
        if(i>0){
            return "success";
        }
        return "fail";
    }
}
