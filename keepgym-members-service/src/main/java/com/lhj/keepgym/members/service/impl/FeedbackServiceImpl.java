package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.members.mapper.FeedbackMapper;
import com.lhj.keepgym.bean.Feedback;
import com.lhj.keepgym.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    //插入用户反馈记录
    @Override
    public String insertFeedback(Feedback feedback) {
        Feedback feedback1 = new Feedback();
        //根据用户id查找数据库中的反馈记录
        feedback1 = feedbackMapper.selectByPrimaryKey(feedback);
        //反馈记录为空则新增
        if (feedback1 == null) {
            int insert = feedbackMapper.insertSelective(feedback);
            if (insert > 0) {
                return "success";
            }
            return "fail";
        } else {//反馈记录不为空则更新
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
