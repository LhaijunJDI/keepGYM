package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.service.ClockService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClockController {
    @Reference
    private ClockService clockService;

    //会员打卡
    @PutMapping("/toClockIn")
    public String toClockIn(String memberId) {
        String result = clockService.addClockIn(memberId);
        return result;
    }

    //会员离开
    @PutMapping("/toClockOut")
    public String toClockOut(String memberId) {
        String result = clockService.addClockOut(memberId);
        return result;
    }
}
