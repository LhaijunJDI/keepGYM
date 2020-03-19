package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.Clock;
import com.lhj.keepgym.service.ClockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Shinelon
 */
@RestController
public class ClockController {
    @Reference
    private ClockService clockService;

    /**
     *
     * @param memberId
     * @return 会员打卡
     */
    @PutMapping("/toClockIn")
    public String toClockIn(String memberId) {
        return clockService.addClockIn(memberId);
    }

    /**
     *
     * @param memberId
     * @return 会员离开
     */
    @PutMapping("/toClockOut")
    public String toClockOut(String memberId) {
        return clockService.addClockOut(memberId);
    }

    /**
     *
     * @return 查询当天打卡人数
     */
    @GetMapping("/toSearchClockMembers")
    public List<Clock> toSearchClockMembers() {
        return clockService.findAllClock();
    }

    /**
     *
     * @return 查询近一周的打卡人数
     */
    @GetMapping("/toSearchAllClock")
    public List<Integer> toSearchAllClock() {
        return clockService.findAllClockInWeek();
    }
}
