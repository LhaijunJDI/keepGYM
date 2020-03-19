package com.lhj.keepgym.manage.mapper;

import com.lhj.keepgym.bean.Coach;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

@Repository
public interface CoachMapper extends Mapper<Coach> {

    @Select("select * from coach")
    List<HashMap<String, Object>> findAllCoachForPoi();
}
