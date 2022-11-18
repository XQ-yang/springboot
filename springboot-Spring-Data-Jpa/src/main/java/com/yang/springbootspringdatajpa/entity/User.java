package com.yang.springbootspringdatajpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName: User
 * @Author: XQ-Yang
 * @Date: 2022/11/16 0016
 * @Description:
 **/
@Entity
@Table(name = "sys_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String phone;
}
