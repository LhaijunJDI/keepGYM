package com.lhj.keepgym.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;

/**
 * (OrderCoach)实体类
 *
 * @author makejava
 * @since 2020-03-06 21:30:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_coach")
public class OrderCoach implements Serializable {
    private static final long serialVersionUID = 328491179791410857L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
    * 教练id
    */
    private String coachId;
    /**
    * 会员id
    */
    private String memberId;
    /**
    * 会员姓名
    */
    private String memberName;
    /**
    * 预约时间
    */
    private Date orderTime;
    @Transient
    private String strOrderTime;
    /**
    * 通知状态
    */
    private String status;

    @Transient
    private String coachName;

    private String num;


}