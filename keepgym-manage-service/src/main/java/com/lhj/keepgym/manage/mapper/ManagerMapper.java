package com.lhj.keepgym.manage.mapper;

import com.lhj.keepgym.bean.Manager;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

@Repository
public interface ManagerMapper extends Mapper<Manager> {
    /**
     * 通过id查找管理员
     * @param id
     * @return
     */
    public Manager findManagerById(Integer id);


    @Select("select * from managers")
    List<HashMap<String, Object>> findAllManagerForPoi();
}
