package com.lhj.keepgym.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (Course)实体类
 *
 * @author makejava
 * @since 2020-02-23 11:50:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class Course implements Serializable {
    private static final long serialVersionUID = 306442475807036798L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String courseId;
    
    private String name;
    
    private String type;
    
    private String num;
    
    private Date startTime;

    @Transient
    private String strStartTime;

    private Date endTime;

    @Transient
    private String strEndTime;

    private String coachId;
    
    private String content;

    private String picSrc;

    @Transient
    private String coachName;
    //private List<Coach> coaches;


}