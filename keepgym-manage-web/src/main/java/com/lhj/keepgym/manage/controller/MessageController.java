package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.Message;
import com.lhj.keepgym.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Shinelon
 */
@RestController
public class MessageController {

    @Reference
    private MessageService messageService;

    /**
     * 添加会员通知
     * @param message
     * @return
     */
    @PutMapping("/toAddMessage")
    public String toAddMessage(@RequestBody Message message) {
        return messageService.insert(message);
    }

    /**
     * 查询已发通知
     * @param memberId
     * @return
     */
    @GetMapping("/toSearchAlreadyNotice")
    public List<Message> toSearchAlreadyNotice(String memberId) {
        return messageService.findNoticeById(memberId);
    }

}
