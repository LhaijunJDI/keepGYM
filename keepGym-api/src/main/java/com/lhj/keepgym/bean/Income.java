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
 * (Income)实体类
 *
 * @author makejava
 * @since 2020-02-28 18:17:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "income")
public class Income implements Serializable {
    private static final long serialVersionUID = 461943755434233900L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String revenueType;
    
    private String money;
    
    private Date createTime;
    
    private String createId;


}