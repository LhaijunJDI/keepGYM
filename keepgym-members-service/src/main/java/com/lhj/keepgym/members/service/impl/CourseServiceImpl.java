package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Course;
import com.lhj.keepgym.members.mapper.CourseMapper;
import com.lhj.keepgym.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course queryById(String classId) {
        return null;
    }

    @Override
    public List<Course> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public Course insert(Course course) {
        return null;
    }

    @Override
    public Course update(Course course) {
        return null;
    }

    @Override
    public boolean deleteById(String classId) {
        return false;
    }

    @Override
    public List<Course> findAllCourse() {
        List<Course>  courses = courseMapper.findAllCourse();
        return courses;
    }
}
