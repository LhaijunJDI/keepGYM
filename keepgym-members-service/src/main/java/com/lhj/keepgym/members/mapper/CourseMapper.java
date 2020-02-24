package com.lhj.keepgym.members.mapper;

import com.lhj.keepgym.bean.Course;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CourseMapper extends Mapper<Course> {
    public List<Course> findAllCourse();
}
