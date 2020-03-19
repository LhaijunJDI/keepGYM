package com.lhj.keepgym.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Feedback;
import com.lhj.keepgym.manage.mapper.FeedbackMapper;
import com.lhj.keepgym.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * @author Shinelon
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public String insertFeedback(Feedback feedback) {
        return null;
    }

    @Override
    public List<HashMap<String, Object>> findAllFeedbackForPoi() {
        return feedbackMapper.findAllFeedbackForPoi();
    }

    @Override
    public List<Feedback> findAllFeedback() {
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        List<Feedback> feedbackList = feedbackMapper.selectAll();
        if(feedbackList!=null){
            for(Feedback feedback:feedbackList){
                feedback.setStrFeedbackTime(simpleDateFormat.format(feedback.getFeedbackTime()));
            }
            return feedbackList;
        }
        return null;
    }
}
