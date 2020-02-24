package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.Course;
import java.util.List;

/**
 * (Course)表服务接口
 *
 * @author makejava
 * @since 2020-02-23 11:50:59
 */
public interface CourseService {

    /**
     * 通过ID查询单条数据
     *
     * @param classId 主键
     * @return 实例对象
     */
    Course queryById(String classId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Course> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    Course insert(Course course);

    /**
     * 修改数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    Course update(Course course);

    /**
     * 通过主键删除数据
     *
     * @param classId 主键
     * @return 是否成功
     */
    boolean deleteById(String classId);

    List<Course> findAllCourse();
}