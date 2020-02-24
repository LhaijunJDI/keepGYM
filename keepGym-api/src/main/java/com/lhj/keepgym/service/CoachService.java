package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.Coach;
import java.util.List;

/**
 * (Coach)表服务接口
 *
 * @author makejava
 * @since 2020-02-23 17:28:36
 */
public interface CoachService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Coach queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Coach> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param coach 实例对象
     * @return 实例对象
     */
    Coach insert(Coach coach);

    /**
     * 修改数据
     *
     * @param coach 实例对象
     * @return 实例对象
     */
    Coach update(Coach coach);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<Coach> findAllCoach();
}