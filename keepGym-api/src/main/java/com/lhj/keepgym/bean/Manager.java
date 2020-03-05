package com.lhj.keepgym.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "managers")
public class Manager implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String password;
    private String gender;
    private String phone;
    private String address;
    private String position;

    private String email;

    private String bankCard;
}
