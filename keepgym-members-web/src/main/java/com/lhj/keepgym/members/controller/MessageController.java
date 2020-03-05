package com.lhj.keepgym.members.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.Coach;
import com.lhj.keepgym.bean.Message;
import com.lhj.keepgym.service.CoachService;
import com.lhj.keepgym.service.MessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class MessageController {
    @Reference
    private MessageService messageService;


    @LoginRequired
    @RequestMapping("/toSearchAllNotice")
    @ResponseBody
    public List<Message> toSearchAllNotice(String memberId){
        List<Message> messages = messageService.findNoticeById(memberId);
        return messages;
    }
}
