package com.lhj.keepgym.manage.mapper;

import com.lhj.keepgym.bean.OrderCoach;
import com.lhj.keepgym.bean.OrderCourse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrderCoachMapper extends Mapper<OrderCoach> {

    List<OrderCoach> findAllOrders();

    @Select("select * from order_coach where coach_id = ${coachId} and member_id = ${memberId}")
    OrderCoach selectOrderCoachById(@Param("coachId") String coachId, @Param("memberId") String memberId);
}
