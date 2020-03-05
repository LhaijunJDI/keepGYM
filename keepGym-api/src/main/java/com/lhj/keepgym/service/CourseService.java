package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.Course;

import java.text.ParseException;
import java.util.List;

/**
 * (Course)表服务接口
 *
 * @author makejava
 * @since 2020-02-23 11:50:59
 */
public interface CourseService {


    List<Course> findAllCourse();

    Course findCourseById(String courseId);

    String updateCourseInfo(Course course) throws ParseException;

    String deleteCourse(String courseId);

    String insertCourse(Course course) throws ParseException;
}