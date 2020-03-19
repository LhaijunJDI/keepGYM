package com.lhj.keepgym.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Coach;
import com.lhj.keepgym.manage.mapper.CoachMapper;
import com.lhj.keepgym.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Shinelon
 */
@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachMapper coachMapper;

    @Override
    public List<Coach> findAllCoach() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<Coach> coaches = coachMapper.selectAll();
        if (coaches != null) {
            for (Coach coach : coaches) {
                if ("1".equals(coach.getGender())) {

                    coach.setGender("男");
                } else {
                    coach.setGender("女");
                }
                coach.setStrEnterTime(simpleDateFormat.format(coach.getEnterTime()));
            }
            return coaches;
        }
        return null;
    }

    @Override
    public Coach findCoachById(String coachId) {
        Coach coach = coachMapper.selectByPrimaryKey(coachId);
        if (coach != null) {
            return coach;
        }
        return null;

    }

    @Override
    public String updateCoach(Coach coach) {
        int i = coachMapper.updateByPrimaryKeySelective(coach);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @Override
    public String deleteCoach(String coachId) {
        int i = coachMapper.deleteByPrimaryKey(coachId);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @Override
    public String insertCoach(Coach coach) {
        Date newDate = new Date();
        coach.setEnterTime(newDate);
        int i = coachMapper.insertSelective(coach);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @Override
    public List<HashMap<String, Object>> findAllCoachForPoi() {
        return coachMapper.findAllCoachForPoi();
    }
}
