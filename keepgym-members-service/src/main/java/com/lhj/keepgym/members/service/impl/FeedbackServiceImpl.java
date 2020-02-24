package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Feedback;
import com.lhj.keepgym.members.mapper.FeedbackMapper;
import com.lhj.keepgym.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public String insertFeedback(Feedback feedback) {
        Feedback feedback1 = new Feedback();
        feedback1 = feedbackMapper.selectByPrimaryKey(feedback);
        if (feedback1 == null) {
            int insert = feedbackMapper.insertSelective(feedback);
            if (insert > 0) {
                return "success";
            }
            return "fail";
        } else {
            Example example = new Example(Feedback.class);
            example.createCriteria().andEqualTo("memberId", feedback.getMemberId());
            int i = feedbackMapper.updateByExampleSelective(feedback, example);
            if(i>0){
                return "success";
            }
            return "fail";
        }
    }
}