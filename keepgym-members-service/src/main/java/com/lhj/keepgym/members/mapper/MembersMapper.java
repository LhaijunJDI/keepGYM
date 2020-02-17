package com.lhj.keepgym.members.mapper;

import com.lhj.keepgym.bean.Members;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MembersMapper extends Mapper<Members> {
    public Members findMemberById(Integer id);
}
