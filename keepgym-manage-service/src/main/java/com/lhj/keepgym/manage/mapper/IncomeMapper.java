package com.lhj.keepgym.manage.mapper;

import com.lhj.keepgym.bean.Income;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author Shinelon
 */
@Repository
public interface IncomeMapper extends Mapper<Income> {
    List<Income> selectAllIncome();

    List<Income> findIncomeLastMonth();

    /**
     *
     * @return 上一周的收入
     */
    @Select("SELECT * FROM income WHERE YEARWEEK(date_format(create_time,'%Y-%m-%d')) = YEARWEEK(now())-1;")
    List<Income> findIncomeAWeek();

    /**
     *
     * @return 上二周的收入
     */
    @Select("SELECT * FROM income WHERE YEARWEEK(date_format(create_time,'%Y-%m-%d')) = YEARWEEK(now())-2;")
    List<Income> findIncomeBWeek();

    /**
     *
     * @return 上三周的收入
     */
    @Select("SELECT * FROM income WHERE YEARWEEK(date_format(create_time,'%Y-%m-%d')) = YEARWEEK(now())-3;")
    List<Income> findIncomeCWeek();

    /**
     *
     * @return 上四周的收入
     */
    @Select("SELECT * FROM income WHERE YEARWEEK(date_format(create_time,'%Y-%m-%d')) = YEARWEEK(now())-4;")
    List<Income> findIncomeDWeek();

    @Select("select * from income")
    List<HashMap<String, Object>> findAllIncome();
}
