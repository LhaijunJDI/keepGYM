package com.lhj.keepgym.members.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.Clock;
import com.lhj.keepgym.service.ClockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ClockController {

    @Reference(group = "member")
    private ClockService clockService;

    /**
     * 根据用户id查找打卡记录
     * @param memberId
     * @return
     */
    @LoginRequired
    @GetMapping("/toSearchAllClock")
    @ResponseBody
    public List<Clock> toSearchAllClock(String memberId){
        List<Clock> allClockById = clockService.findAllClockById(memberId);
        return allClockById;
    }

}
