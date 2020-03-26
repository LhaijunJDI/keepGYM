package com.lhj.keepgym.manage.mapper;

import com.lhj.keepgym.bean.Clock;
import com.lhj.keepgym.bean.Members;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Shinelon
 */
@Repository
public interface ClockMapper extends Mapper<Clock> {

    /**
     * 查询当天打卡的会员人数
     *
     * @return
     */
    @Select(" select * from clock where TO_DAYS(clock_in_time) = TO_DAYS(NOW())")
    List<Clock> findCurrentDayMembers();

    /**
     * 查询一天前的打卡人数
     *
     * @return
     */
    @Select("select count(*) from clock where TO_DAYS(clock_in_time) = TO_DAYS(NOW())-1")
    Integer findADayMembers();

    /**
     * 查询二天前的打卡人数
     *
     * @return
     */
    @Select("select count(*) from clock where TO_DAYS(clock_in_time) = TO_DAYS(NOW())-2")
    Integer findBDayMembers();

    /**
     * 查询三天前的打卡人数
     *
     * @return
     */
    @Select("select count(*) from clock where TO_DAYS(clock_in_time) = TO_DAYS(NOW())-3")
    Integer findCDayMembers();

    /**
     * 查询四天前的打卡人数
     *
     * @return
     */
    @Select("select count(*) from clock where TO_DAYS(clock_in_time) = TO_DAYS(NOW())-4")
    Integer findDDayMembers();

    /**
     * 查询五天前的打卡人数
     *
     * @return
     */
    @Select("select count(*) from clock where TO_DAYS(clock_in_time) = TO_DAYS(NOW())-5")
    Integer findEDayMembers();

    /**
     * 查询六天前的打卡人数
     *
     * @return
     */
    @Select("select count(*) from clock where TO_DAYS(clock_in_time) = TO_DAYS(NOW())-6")
    Integer findFDayMembers();

    /**
     * 查询七天前的打卡人数
     *
     * @return
     */
    @Select("select count(*) from clock where TO_DAYS(clock_in_time) = TO_DAYS(NOW())-7")
    Integer findGDayMembers();

    /**
     * 更新会员离开的时间
     *
     * @param memberId
     * @return
     */
    @Update("update clock set clock_out_time = DATE_FORMAT(NOW(),'%Y-%m-%d %H:%i:%s')  where member_id = #{memberId} and clock_in_time = clock_out_time")
    int updateClockOutTime(String memberId);
}
