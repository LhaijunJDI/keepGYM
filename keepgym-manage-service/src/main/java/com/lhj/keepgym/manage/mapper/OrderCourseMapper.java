package com.lhj.keepgym.manage.mapper;

import com.lhj.keepgym.bean.OrderCourse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Shinelon
 */
@Repository
public interface OrderCourseMapper  extends Mapper<OrderCourse> {
    List<OrderCourse> findAllOrders();

    @Select("select * from order_course where course_id = ${courseId} and member_id = ${memberId}")
    OrderCourse selectOrderCourseById(@Param("courseId") String courseId,@Param("memberId") String memberId);
}
