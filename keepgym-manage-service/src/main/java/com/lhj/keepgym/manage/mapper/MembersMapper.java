package com.lhj.keepgym.manage.mapper;

import com.lhj.keepgym.bean.Members;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author Shinelon
 */
@Repository
public interface MembersMapper extends Mapper<Members> {
    /**
     *
     * @return 当天新增会员
     */
    @Select("select * from members where date_format(create_time,'%Y-%m')=date_format(now(),'%Y-%m')")
    List<Members> findNewMembers();

    List<HashMap<String, Object>> findAllMembers();

    /**
     * s
     * @return 查询全部会员信息用于导出excel
     */
    @Select("select * from members")
    List<HashMap<String, Object>> findAllMembersForPoi();
}
