package com.lhj.keepgym.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * (OrderCourse)实体类
 *
 * @author makejava
 * @since 2020-02-23 21:37:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_course")
public class OrderCourse implements Serializable {
    private static final long serialVersionUID = 735227210569589056L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String courseId;
    
    private String memberId;
    
    private String memberName;
    
    private Date orderTime;



}