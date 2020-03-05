package com.lhj.keepgym.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Clock;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.manage.mapper.ClockMapper;
import com.lhj.keepgym.manage.mapper.MembersMapper;
import com.lhj.keepgym.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ClockServiceImpl implements ClockService {

    @Autowired
    private ClockMapper clockMapper;

    @Autowired
    private MembersMapper membersMapper;

    @Override
    public List<Clock> findAllClockById(String memberId) {
        return null;
    }

    @Override
    public String addClockIn(String memberId) {
        if (memberId == null || memberId.equals(' ')) {
            return "fail";
        }
        Members members = membersMapper.selectByPrimaryKey(memberId);
        if (members != null && "可用".equals(members.getStatus())) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date clockInDate = new Date();
            simpleDateFormat.format(clockInDate);
            Clock clock = new Clock();
            clock.setMemberId(memberId);
            clock.setClockInTime(clockInDate);
            clock.setClockOutTime(clockInDate);
            int i = clockMapper.insertSelective(clock);
            if (i > 0) {
                return "success";
            }
            return "fail";
        }

        return "unused";
    }

    @Override
    public String addClockOut(String memberId) {
        if (memberId == null || memberId.equals(' ')) {
            return "fail";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date clockOutDate = new Date();
        simpleDateFormat.format(clockOutDate);
        Example example = new Example(Clock.class);
        example.createCriteria().andEqualTo("memberId", memberId);
        Clock clock = new Clock();
        clock.setClockOutTime(clockOutDate);
        int i = clockMapper.updateByExampleSelective(clock, example);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }
}
