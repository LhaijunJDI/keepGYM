package com.lhj.keepgym.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "members")
public class Members implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String username;
    private String password;
    private String gender;
    private String phone;
    private String address;
    private String createId;
    private Date createTime;

    private Date endTime;

    @Transient
    private String strEndTime;

    @Transient
    private String expire;

    //剩余天数
    @Transient
    private String remainTime;

    private String email;

    private String level;

    private String status;

    private String confirm;


}
