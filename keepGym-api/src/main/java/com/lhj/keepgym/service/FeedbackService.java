package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.Feedback;
import tk.mybatis.mapper.common.Mapper;

public interface FeedbackService {
    public String insertFeedback(Feedback feedback);
}
