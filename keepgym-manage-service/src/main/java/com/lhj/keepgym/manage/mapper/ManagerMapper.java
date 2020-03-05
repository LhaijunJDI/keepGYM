package com.lhj.keepgym.manage.mapper;

import com.lhj.keepgym.bean.Manager;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ManagerMapper extends Mapper<Manager> {
    /**
     * 通过id查找管理员
     * @param id
     * @return
     */
    public Manager findManagerById(Integer id);
}
