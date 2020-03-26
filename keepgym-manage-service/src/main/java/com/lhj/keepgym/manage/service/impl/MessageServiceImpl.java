package com.lhj.keepgym.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.bean.Message;
import com.lhj.keepgym.bean.OrderCoach;
import com.lhj.keepgym.bean.OrderCourse;
import com.lhj.keepgym.manage.mapper.MembersMapper;
import com.lhj.keepgym.manage.mapper.MessageMapper;
import com.lhj.keepgym.manage.mapper.OrderCoachMapper;
import com.lhj.keepgym.manage.mapper.OrderCourseMapper;
import com.lhj.keepgym.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Shinelon
 */
@Service(group = "manage")
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private OrderCourseMapper orderCourseMapper;

    @Autowired
    private OrderCoachMapper orderCoachMapper;

    @Autowired
    private MembersMapper membersMapper;

    @Override
    public Message queryById(Integer id) {
        return null;
    }

    @Override
    public List<Message> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    @Transactional
    public String insert(Message message) {
        int i1 = 0;
        message.setSendTime(new Date());
        message.setStatus("0");
        int i = messageMapper.insert(message);
        if (i > 0) {
            //课程预约通知
            if("1".equals(message.getWh())){
                OrderCourse orderCourse = new OrderCourse();
                orderCourse.setStatus("1");
                Example example = new Example(OrderCourse.class);
                example.createCriteria().andEqualTo("memberId",message.getReceiveId());
                 i1 = orderCourseMapper.updateByExampleSelective(orderCourse,example);
            }
            //私教预约通知
            if("2".equals(message.getWh())){
                OrderCoach orderCoach = new OrderCoach();
                orderCoach.setStatus("1");
                Example example = new Example(OrderCoach.class);
                example.createCriteria().andEqualTo("memberId",message.getReceiveId());
                i1 = orderCoachMapper.updateByExampleSelective(orderCoach,example);
            }
            //会员过期通知
            if("3".equals(message.getWh())){
                Members members = new Members();
                members.setId(message.getReceiveId());
                members.setConfirm("1");
                i1 = membersMapper.updateByPrimaryKeySelective(members);
            }
            if("4".equals(message.getWh())){
                i1 = 1;
            }
            if (i1 > 0) {
                return "success";
            }
        }
        return "fail";
    }

    @Override
    public Message update(Message message) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public List<Message> findNoticeById(String memberId) {
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Example example = new Example(Message.class);
        example.createCriteria().andEqualTo("receiveId",memberId);
        List<Message> messages = messageMapper.selectByExample(example);
        if(messages!=null){
            for(Message message:messages){
                message.setStrSendTime(simpleDateFormat.format(message.getSendTime()));
            }
            return messages;
        }
        return null;
    }

    @Override
    public List<Message> findCheckNoticeById(String memberId) {
        return null;
    }

    @Override
    public String updateAllNoticeStatus(String memberId) {
        return null;
    }

    @Override
    public String updateNoticeStatus(String id) {
        return null;
    }

    @Override
    public String deleteNoticeById(String id) {
        return null;
    }

    @Override
    public String deleteAllNoticeById(String memberId) {
        return null;
    }
}
