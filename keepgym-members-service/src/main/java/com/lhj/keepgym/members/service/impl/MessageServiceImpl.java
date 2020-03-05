package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Message;
import com.lhj.keepgym.members.mapper.MessageMapper;
import com.lhj.keepgym.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

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
        Message message = new Message();
        message.setReceiveId(memberId);
        List<Message> messages = messageMapper.select(message);
        if(messages!=null){
            return messages;
        }
        return null;
    }
}
