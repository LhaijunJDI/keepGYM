package com.lhj.keepgym.members.mapper;

import com.lhj.keepgym.bean.Manager;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ManagerMapper extends Mapper<Manager> {
    public Manager findManagerById(Integer id);
}
