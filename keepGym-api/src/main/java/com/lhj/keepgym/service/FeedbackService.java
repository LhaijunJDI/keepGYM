package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.Feedback;

import java.util.HashMap;
import java.util.List;

public interface FeedbackService {
    public String insertFeedback(Feedback feedback);

    List<HashMap<String, Object>> findAllFeedbackForPoi();

    List<Feedback> findAllFeedback();
}
