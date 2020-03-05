package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Course;
import com.lhj.keepgym.members.mapper.CourseMapper;
import com.lhj.keepgym.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findAllCourse() {
        List<Course>  courses = courseMapper.findAllCourse();
        return courses;
    }

    @Override
    public Course findCourseById(String courseId) {
        return null;
    }

    @Override
    public String updateCourseInfo(Course course) {
        return null;
    }

    @Override
    public String deleteCourse(String courseId) {
        return null;
    }

    @Override
    public String insertCourse(Course course) throws ParseException {
        return null;
    }
}
