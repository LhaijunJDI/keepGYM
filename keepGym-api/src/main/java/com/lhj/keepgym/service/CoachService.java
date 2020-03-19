package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.Coach;

import java.util.HashMap;
import java.util.List;

/**
 * (Coach)表服务接口
 *
 * @author makejava
 * @since 2020-02-23 17:28:36
 */
public interface CoachService {


    List<Coach> findAllCoach();

    Coach findCoachById(String coachId);

    String updateCoach(Coach coach);

    String deleteCoach(String coachId);

    String insertCoach(Coach coach);

    List<HashMap<String, Object>> findAllCoachForPoi();
}