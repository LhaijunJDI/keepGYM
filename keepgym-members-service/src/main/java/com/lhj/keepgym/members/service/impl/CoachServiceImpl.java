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
    public Coach queryById(String id) {
        return null;
    }

    @Override
    public List<Coach> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public Coach insert(Coach coach) {
        return null;
    }

    @Override
    public Coach update(Coach coach) {
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public List<Coach> findAllCoach() {
        List<Coach> coaches = coachMapper.selectAll();
        return coaches;
    }
}
