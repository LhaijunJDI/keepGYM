package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.OrderCourse;
import java.util.List;

/**
 * (OrderCourse)表服务接口
 *
 * @author makejava
 * @since 2020-02-23 21:37:15
 */
public interface OrderCourseService {

    /**
     * 通过ID查询单条数据
     *
     * @param courseId 主键
     * @return 实例对象
     */
    OrderCourse queryById(String courseId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OrderCourse> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param orderCourse 实例对象
     * @return 实例对象
     */
    OrderCourse insert(OrderCourse orderCourse);

    /**
     * 修改数据
     *
     * @param orderCourse 实例对象
     * @return 实例对象
     */
    OrderCourse update(OrderCourse orderCourse);

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 是否成功
     */
    boolean deleteById(String courseId);

    String saveOrderCourse(OrderCourse orderCourse);
}