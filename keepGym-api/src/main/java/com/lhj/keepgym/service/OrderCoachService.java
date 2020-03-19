package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.OrderCoach;
import java.util.List;

/**
 * (OrderCoach)表服务接口
 *
 * @author makejava
 * @since 2020-03-06 21:30:24
 */
public interface OrderCoachService {

    String saveOrderCoach(OrderCoach orderCoach);

    List<OrderCoach> findAllOrder();

    String buyCoachClass(OrderCoach orderCoach, String managerId);

    String updateOrderCoachNum(String coachId, String memberId);
}