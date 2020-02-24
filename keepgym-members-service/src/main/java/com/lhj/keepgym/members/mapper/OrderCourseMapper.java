package com.lhj.keepgym.members.mapper;

import com.lhj.keepgym.bean.OrderCourse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface OrderCourseMapper extends Mapper<OrderCourse> {
    public OrderCourse findOrderCourseById(@Param("memberId") String memberId, @Param("courseId") String courseId);
    public int updateCourseNum(String courseId);
}
