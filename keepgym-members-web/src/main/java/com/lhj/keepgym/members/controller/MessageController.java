package com.lhj.keepgym.members.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.Coach;
import com.lhj.keepgym.bean.Message;
import com.lhj.keepgym.service.CoachService;
import com.lhj.keepgym.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Shinelon
 */
@RestController
public class MessageController {
    @Reference
    private MessageService messageService;


    @LoginRequired
    @RequestMapping("/toSearchAllNotice")
    public List<Message> toSearchAllNotice(String memberId){
        return messageService.findNoticeById(memberId);
    }

    @LoginRequired
    @RequestMapping("/toSearchAllCheckNotice")
    public List<Message> toSearchAllCheckNotice(String memberId){
        return messageService.findCheckNoticeById(memberId);
    }


    @LoginRequired
    @PostMapping("/setAllNoticeCheck")
    public String setAllNoticeCheck(String memberId){
        return messageService.updateAllNoticeStatus(memberId);
    }

    @LoginRequired
    @PostMapping("/setNoticeCheck")
    public String setNoticeCheck(String id){
        return messageService.updateNoticeStatus(id);
    }


    @LoginRequired
    @DeleteMapping("/deleteNotice")
    public String deleteNotice(String id){
        return messageService.deleteNoticeById(id);
    }

    @LoginRequired
    @DeleteMapping("/deleteAllNotice")
    public String deleteAllNotice(String memberId){
        return messageService.deleteAllNoticeById(memberId);
    }
}
