package com.lhj.keepgym.members.mapper;

import com.lhj.keepgym.bean.OrderCourse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface OrderCourseMapper extends Mapper<OrderCourse> {
    /**
     * 根据用户id和课程id查找课程是否被用户预订
     * @param memberId
     * @param courseId
     * @return
     */
    public OrderCourse findOrderCourseById(@Param("memberId") String memberId, @Param("courseId") String courseId);

    /**
     * 更新课程剩余人数
     * @param courseId
     * @return
     */
    public int updateCourseNum(String courseId);
}
