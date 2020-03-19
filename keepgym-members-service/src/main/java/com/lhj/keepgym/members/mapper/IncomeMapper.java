package com.lhj.keepgym.members.mapper;

import com.lhj.keepgym.bean.Income;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Shinelon
 */
@Repository
public interface IncomeMapper extends Mapper<Income> {

}
