package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Coach;
import com.lhj.keepgym.members.mapper.CoachMapper;
import com.lhj.keepgym.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachMapper coachMapper;

    @Override
    public List<Coach> findAllCoach() {
        List<Coach> coaches = coachMapper.selectAll();
        return coaches;
    }

    @Override
    public Coach findCoachById(String coachId) {
        return null;
    }

    @Override
    public String updateCoach(Coach coach) {
        return null;
    }

    @Override
    public String deleteCoach(String coachId) {
        return null;
    }

    @Override
    public String insertCoach(Coach coach) {
        return null;
    }
}
