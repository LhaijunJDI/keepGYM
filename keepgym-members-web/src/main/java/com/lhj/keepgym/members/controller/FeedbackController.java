package com.lhj.keepgym.members.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.Feedback;
import com.lhj.keepgym.service.FeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FeedbackController {
    @Reference
    private FeedbackService feedbackService;

    //前往意见反馈页面
    @LoginRequired
    @RequestMapping("/toMemberFeedback")
    public String toMemberFeedback(String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "memberFeedback";
    }

    //保存用户反馈信息
    @PostMapping("/saveFeedback")
    @ResponseBody
    public String saveFeedback(@RequestBody Feedback feedback){
        String result = feedbackService.insertFeedback(feedback);
        return result;
    }
}
