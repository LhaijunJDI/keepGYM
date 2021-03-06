package com.lhj.keepgym.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "feedback")
public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String memberId;
    private String memberName;
    private String phone;
    private String resource1;
    private String resource2;
    private String resource3;
    private String resource4;
    private String resource5;
    private Date feedbackTime;

    @Transient
    private String strFeedbackTime;

}
