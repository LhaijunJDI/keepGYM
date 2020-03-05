package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.bean.Message;
import com.lhj.keepgym.service.MessageService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Reference
    private MessageService messageService;
    @PutMapping("/toAddMessage")
    public String toAddMessage(@RequestBody Message message) {
        String result = messageService.insert(message);
        return result;
    }
}
