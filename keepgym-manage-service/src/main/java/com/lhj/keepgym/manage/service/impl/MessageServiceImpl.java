package com.lhj.keepgym.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Message;
import com.lhj.keepgym.manage.mapper.MessageMapper;
import com.lhj.keepgym.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
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
        message.setSendTime(new Date());
        int i = messageMapper.insertSelective(message);
        if(i>0){
            return "success";
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
        return null;
    }
}
