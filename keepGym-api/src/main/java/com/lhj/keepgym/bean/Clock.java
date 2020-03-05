package com.lhj.keepgym.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;

/**
 * (Clock)实体类
 *
 * @author makejava
 * @since 2020-02-24 22:48:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clock")
public class Clock implements Serializable {
    private static final long serialVersionUID = -48177872062722445L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
    * 会员id
    */
    private String memberId;
    /**
    * 打卡时间
    */
    private Date clockInTime;
    @Transient
    private String strClockInTime;
    @Transient
    private String strClockInTime1;
    /**
    * 离开时间
    */
    private Date clockOutTime;
    @Transient
    private String strClockOutTime;

    @Transient
    private String sportTime;


}