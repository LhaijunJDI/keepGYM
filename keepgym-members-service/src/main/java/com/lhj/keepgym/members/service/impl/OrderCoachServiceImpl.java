package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Course;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.bean.OrderCoach;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.members.mapper.MembersMapper;
import com.lhj.keepgym.members.mapper.OrderCoachMapper;
import com.lhj.keepgym.service.OrderCoachService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderCoachServiceImpl implements OrderCoachService {
    @Autowired
    private MembersMapper membersMapper;

    @Autowired
    private OrderCoachMapper orderCoachMapper;

    @Override
    public String saveOrderCoach(OrderCoach orderCoach) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date orderTime = new Date();
        String memberId = orderCoach.getMemberId();
        String coachId = orderCoach.getCoachId();
        if(coachId==null||memberId==null){
            return "fail";
        }
        //根据memberId查找用户
        Members members = membersMapper.selectByPrimaryKey(memberId);
        orderCoach.setMemberName(members.getUsername());
        orderCoach.setOrderTime(orderTime);
        //根据用户id和教练id查找预约信息
        OrderCoach orderCoach1=orderCoachMapper.findOrderCoachById(memberId,coachId);
        //预约信息为空则说明该用户并未预约该教练
        if (orderCoach1 == null){
            orderCoach.setStatus("0");
            int result = orderCoachMapper.insertSelective(orderCoach);
            if(result>0){
                    return "success";
            }
            return "fail";
        } else {
            //预约信息不为空则说明该用户已经预约过该教练
            return "already";
        }
    }

    @Override
    public List<OrderCoach> findAllOrder() {
        return null;
    }

    @Override
    public String buyCoachClass(OrderCoach orderCoach, String managerId) {
        return null;
    }

    @Override
    public String updateOrderCoachNum(String coachId, String memberId) {
        return null;
    }
}
