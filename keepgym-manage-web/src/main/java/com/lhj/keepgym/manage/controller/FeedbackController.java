package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.bean.Feedback;
import com.lhj.keepgym.service.FeedbackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedbackController {

    @Reference
    private FeedbackService feedbackService;

    /**
     * 查询会员反馈信息
     * @return
     */
    @GetMapping("/toSearchAllFeedback")
    public List<Feedback> toSearchAllFeedback(){
        return feedbackService.findAllFeedback();
    }
}
