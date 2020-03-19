package com.lhj.keepgym.manage.mapper;

import com.lhj.keepgym.bean.Feedback;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

@Repository
public interface FeedbackMapper extends Mapper<Feedback> {
    @Select("select * from feedback")
    List<HashMap<String, Object>> findAllFeedbackForPoi();
}
