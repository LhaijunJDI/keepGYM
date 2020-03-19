package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.Clock;
import java.util.List;
import java.util.Map;

/**
 * (Clock)表服务接口
 *
 * @author makejava
 * @since 2020-02-24 22:48:10
 */
public interface ClockService {


    List<Clock> findAllClockById(String memberId);

    String addClockIn(String memberId);

    String addClockOut(String memberId);

    List<Clock> findAllClock();

    List<Integer> findAllClockInWeek();
}