package com.lhj.keepgym.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;

/**
 * (Coach)实体类
 *
 * @author makejava
 * @since 2020-02-23 17:27:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coach")
public class Coach implements Serializable {
    private static final long serialVersionUID = -91131362026802681L;
    /**
    * 教练id
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    /**
    * 教练姓名
    */
    private String name;
    /**
    * 教练电话
    */
    private String phone;
    /**
    * 教练性别
    */
    private String gender;
    /**
    * 教练居住地址
    */
    private String address;
    /**
    * 教练毕业学校
    */
    private String graduSchool;
    /**
    * 教练特色
    */
    private String type;
    /**
    * 教练个性签名
    */
    private String content;
    /**
    * 入职时间
    */
    private Date enterTime;

    @Transient
    private String strEnterTime;

    private String coachPic;

    private String teachTime;

    private String bankCard;


}