package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Message;
import com.lhj.keepgym.members.mapper.MessageMapper;
import com.lhj.keepgym.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;


    @Override
    public Message queryById(Integer id) {
        return null;
    }

    @Override
    public List<Message> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public String insert(Message message) {
        return null;
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Example example = new Example(Message.class);
        example.createCriteria().andEqualTo("receiveId", memberId).andEqualTo("status", 0);
        List<Message> messages = messageMapper.selectByExample(example);
        if (messages != null) {
            for (Message message : messages) {
                message.setStrSendTime(simpleDateFormat.format(message.getSendTime()));
            }
            return messages;
        }
        return null;
    }

    @Override
    public List<Message> findCheckNoticeById(String memberId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Example example = new Example(Message.class);
        example.createCriteria().andEqualTo("receiveId", memberId).andEqualTo("status", 1);
        List<Message> messages = messageMapper.selectByExample(example);
        if (messages != null) {
            for (Message message : messages) {
                message.setStrSendTime(simpleDateFormat.format(message.getSendTime()));
            }
            return messages;
        }
        return null;
    }

    @Override
    public String updateAllNoticeStatus(String memberId) {
        Example example = new Example(Message.class);
        example.createCriteria().andEqualTo("receiveId",memberId);
        Message message = null;
        List<Message> messageList = messageMapper.selectByExample(example);
        Iterator<Message> iterator = messageList.iterator();
        while (iterator.hasNext()) {
            message = iterator.next();
            message.setStatus("1");
            int i = messageMapper.updateByPrimaryKeySelective(message);
            if(i<=0){
               return "fail";
            }
        }
        return "success";
    }

    @Override
    public String updateNoticeStatus(String id) {
        Message message = new Message();
        message.setId(Integer.parseInt(id));
        message.setStatus("1");
        int i = messageMapper.updateByPrimaryKeySelective(message);
        if(i>0){
            return "success";
        }
        return "fail";
    }

    @Override
    public String deleteNoticeById(String id) {
        int i = messageMapper.deleteByPrimaryKey(Integer.parseInt(id));
        if(i>0){
            return "success";
        }
        return "fail";
    }

    @Override
    public String deleteAllNoticeById(String memberId) {
        Message message = new Message();
        Example example = new Example(Message.class);
        example.createCriteria().andEqualTo("receiveId",memberId);
        List<Message> messageList = messageMapper.selectByExample(example);
        Iterator<Message> iterator = messageList.iterator();
        while (iterator.hasNext()){
            message = iterator.next();
            int i = messageMapper.delete(message);
            if(i<=0){
                return "fail";
            }
        }
        return "success";
    }
}
