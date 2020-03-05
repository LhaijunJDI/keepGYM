package com.lhj.keepgym.members.mapper;

import com.lhj.keepgym.bean.Members;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MembersMapper extends Mapper<Members> {
    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    public Members findMemberById(String id);
}
