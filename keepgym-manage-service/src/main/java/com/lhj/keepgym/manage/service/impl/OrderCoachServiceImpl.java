package com.lhj.keepgym.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Income;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.bean.OrderCoach;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.manage.mapper.IncomeMapper;
import com.lhj.keepgym.manage.mapper.MembersMapper;
import com.lhj.keepgym.manage.mapper.OrderCoachMapper;
import com.lhj.keepgym.manage.mapper.OrderCourseMapper;
import com.lhj.keepgym.service.OrderCoachService;
import com.lhj.keepgym.service.OrderCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Shinelon
 */
@Service(group = "manage")
public class OrderCoachServiceImpl implements OrderCoachService {

    @Autowired
    private OrderCoachMapper orderCoachMapper;

    @Autowired
    private MembersMapper membersMapper;

    @Autowired
    private IncomeMapper incomeMapper;

    @Override
    public String saveOrderCoach(OrderCoach orderCoach) {
        return null;
    }

    @Override
    public List<OrderCoach> findAllOrder() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<OrderCoach> allOrders = orderCoachMapper.findAllOrders();
        if (allOrders != null) {
            for (OrderCoach orderCoach : allOrders) {
                if ("0".equals(orderCoach.getStatus())) {
                    orderCoach.setStatus("未通知");
                }
                if ("1".equals(orderCoach.getStatus())) {
                    orderCoach.setStatus("已通知");
                }
                orderCoach.setStrOrderTime(simpleDateFormat.format(orderCoach.getOrderTime()));
            }
            return allOrders;
        }
        return null;
    }

    @Override
    @Transactional
    public String buyCoachClass(OrderCoach orderCoach, String managerId) {
        OrderCoach orderCoach1 = orderCoachMapper.selectOrderCoachById(orderCoach.getCoachId(),orderCoach.getMemberId());
        if (orderCoach1 == null) {
            Members members = membersMapper.selectByPrimaryKey(orderCoach.getMemberId());
            if ("禁用".equals(members.getStatus())) {
                return "fail";
            }
            orderCoach.setMemberName(members.getUsername());
            orderCoach.setOrderTime(new Date());
            orderCoach.setStatus("0");
            int insert = orderCoachMapper.insert(orderCoach);
            if (insert > 0) {
                Income income = new Income();
                income.setCreateId(managerId);
                income.setCreateTime(new Date());
                income.setRevenueType("私教收入");
                if ("7".equals(orderCoach.getNum())) {
                    income.setMoney("1400.00");
                }
                if ("15".equals(orderCoach.getNum())) {
                    income.setMoney("2500.00");
                }
                if ("30".equals(orderCoach.getNum())) {
                    income.setMoney("4000.00");
                }
                int insert1 = incomeMapper.insert(income);
                if (insert1 > 0) {
                    return "success";
                }
            }
            return "fail";
        }
        int num = Integer.parseInt(orderCoach1.getNum()) + Integer.parseInt(orderCoach.getNum());
        orderCoach1.setNum(String.valueOf(num));
        orderCoach1.setOrderTime(new Date());
        orderCoach1.setStatus("0");
        int i = orderCoachMapper.updateByPrimaryKeySelective(orderCoach1);
        if (i > 0) {
            Income income = new Income();
            income.setCreateId(managerId);
            income.setCreateTime(new Date());
            income.setRevenueType("私教收入");

            if ("7".equals(orderCoach.getNum())) {
                income.setMoney("1400.00");
            }
            if ("15".equals(orderCoach.getNum())) {
                income.setMoney("2500.00");
            }
            if ("30".equals(orderCoach.getNum())) {
                income.setMoney("4000.00");
            }
            int insert1 = incomeMapper.insert(income);
            if (insert1 > 0) {
                return "success";
            }
        }
        return "fail";
    }

    @Override
    public String updateOrderCoachNum(String coachId, String memberId) {
        OrderCoach orderCoach = orderCoachMapper.selectOrderCoachById(coachId, memberId);
        if(orderCoach.getNum().equals("0")){
            return "zero";
        }
            int num = Integer.parseInt(orderCoach.getNum())-1;
            orderCoach.setNum(String.valueOf(num));
        int i = orderCoachMapper.updateByPrimaryKeySelective(orderCoach);
        if(i>0){
            return "success";
        }
        return "fail";
    }
}
