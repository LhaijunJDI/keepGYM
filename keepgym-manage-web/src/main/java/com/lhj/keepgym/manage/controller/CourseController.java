package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.bean.Course;
import com.lhj.keepgym.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class CourseController {

    @Reference
    private CourseService courseService;

    // 查找全部课程信息
    @GetMapping("/toSearchAllCourse")
    public List<Course> toSearchAllCourse() {
        List<Course> courses = courseService.findAllCourse();
        return courses;
    }

    //    查找课程信息
    @GetMapping("/toSearchCourseInfo")
    public Course toSearchCourseInfo(String courseId) {
        Course course = courseService.findCourseById(courseId);
        return course;
    }


    //    修改课程
    @PostMapping("/toAlterCourseInfo")
    public String toAlterCourseInfo(@RequestBody Course course) throws ParseException {
        String result = courseService.updateCourseInfo(course);
        return result;
    }

    //删除课程
    @DeleteMapping("/toDeleteCourse")
    public String toDeleteCourse(String courseId) {
        String result = courseService.deleteCourse(courseId);
        return result;
    }

    //发布课程
    @PutMapping("/toAddCourse")
    public String toAddCourse(@RequestBody Course course) throws ParseException {
        String result = courseService.insertCourse(course);
        return result;
    }
}
