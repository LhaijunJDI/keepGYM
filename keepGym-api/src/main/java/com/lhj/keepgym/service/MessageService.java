package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.Message;
import java.util.List;

/**
 * (Message)表服务接口
 *
 * @author makejava
 * @since 2020-03-03 20:07:56
 */
public interface MessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Message queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Message> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    String insert(Message message);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message update(Message message);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<Message> findNoticeById(String memberId);

    List<Message> findCheckNoticeById(String memberId);

    String updateAllNoticeStatus(String memberId);

    String updateNoticeStatus(String id);

    String deleteNoticeById(String id);

    String deleteAllNoticeById(String memberId);
}