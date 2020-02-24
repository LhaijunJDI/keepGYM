package com.lhj.keepgym.members.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.bean.Course;
import com.lhj.keepgym.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class CourseController {

    @Reference
    private CourseService courseService;

    //前往课程详情页面
    @RequestMapping("/toMemberCourse")
    public String toMemberCourse(String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "memberCourse";
    }

    //查找全部课程信息
    @GetMapping("/toSearchAllCourse")
    @ResponseBody
    public List<Course> toSearchAllCourse(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<Course> courses = courseService.findAllCourse();
        for (Course course:courses){
            String startTime = simpleDateFormat.format(course.getStartTime());
            String endTime = simpleDateFormat.format(course.getEndTime());
            course.setStrStartTime(startTime);
            course.setStrEndTime(endTime);
        }
        System.out.println(courses.get(0));
        return courses;
    }
}
