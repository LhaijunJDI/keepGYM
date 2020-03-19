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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Shinelon
 */
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
        int i = clockMapper.updateClockOutTime(memberId);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @Override
    public List<Clock> findAllClock() {
        return clockMapper.findCurrentDayMembers();
    }

    @Override
    public List<Integer> findAllClockInWeek() {
        List<Integer> list =new ArrayList<Integer>();
        Integer aDayMembers = clockMapper.findADayMembers();
        Integer bDayMembers = clockMapper.findBDayMembers();
        Integer cDayMembers = clockMapper.findCDayMembers();
        Integer dDayMembers = clockMapper.findDDayMembers();
        Integer eDayMembers = clockMapper.findEDayMembers();
        Integer fDayMembers = clockMapper.findFDayMembers();
        Integer gDayMembers = clockMapper.findGDayMembers();
        list.add(aDayMembers);
        list.add(bDayMembers);
        list.add(cDayMembers);
        list.add(dDayMembers);
        list.add(eDayMembers);
        list.add(fDayMembers);
        list.add(gDayMembers);
        return list;
    }

}
