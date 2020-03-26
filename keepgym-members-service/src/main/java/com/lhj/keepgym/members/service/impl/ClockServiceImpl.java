package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.members.mapper.ClockMapper;
import com.lhj.keepgym.bean.Clock;
import com.lhj.keepgym.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Shinelon
 */
@Service(group = "member")
public class ClockServiceImpl implements ClockService {
    @Autowired
    private ClockMapper clockMapper;



    @Override
    public List<Clock> findAllClockById(String memberId) {
        //带小时和分钟的格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //不带小时和分钟的格式
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

        Example example = new Example(Clock.class);
        //根据memberId来查找信息
        example.createCriteria().andEqualTo("memberId",memberId);
        List<Clock> clocks = clockMapper.selectByExample(example);
        if(clocks == null){
            return null;
        }
        //遍历查找出来的信息并将date格式转换为所需格式
        for (Clock clock:clocks){
            String sportTime = String.valueOf((clock.getClockOutTime().getTime() - clock.getClockInTime().getTime())/(60*1000));
            clock.setStrClockInTime1(simpleDateFormat1.format(clock.getClockInTime()));
            clock.setStrClockInTime(simpleDateFormat.format(clock.getClockInTime()));
            clock.setStrClockOutTime(simpleDateFormat.format(clock.getClockOutTime()));
            clock.setSportTime(sportTime);
        }
        return clocks;
    }

    @Override
    public String addClockIn(String memberId) {
        return null;
    }

    @Override
    public String addClockOut(String memberId) {
        return null;
    }

    @Override
    public List<Clock> findAllClock() {
        return null;
    }

    @Override
    public List<Integer> findAllClockInWeek() {
        return null;
    }


}
