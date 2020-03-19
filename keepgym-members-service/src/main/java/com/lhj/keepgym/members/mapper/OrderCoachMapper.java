package com.lhj.keepgym.members.mapper;

import com.lhj.keepgym.bean.OrderCoach;
import com.lhj.keepgym.bean.OrderCourse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface OrderCoachMapper extends Mapper<OrderCoach> {

    OrderCoach findOrderCoachById(@Param("memberId") String memberId,  @Param("coachId") String coachId);
}
