package com.lhj.keepgym.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;

/**
 * (Message)实体类
 *
 * @author makejava
 * @since 2020-03-03 20:07:51
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message implements Serializable {
    private static final long serialVersionUID = 861607704739439014L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 发送人
     */
    private String sendId;
    /**
     * 接收人
     */
    private String receiveId;
    /**
     * 通知内容
     */
    private String content;

    private String type;

    private String status;

    /**
     * 发送时间
     */
    private Date sendTime;

    @Transient
    private String strSendTime;
    @Transient
    private String wh;
}