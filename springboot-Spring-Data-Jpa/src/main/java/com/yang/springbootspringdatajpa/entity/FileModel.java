package com.yang.springbootspringdatajpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: 小强
 * @date: 2022/11/18 0018
 * @IDE: IntelliJ IDEA
 */
@Data
@Entity
@Table(name = "sys_file")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String fileName;
    private String type;
    private Long size;
    private String md5;
    private String newName;
    private String downloadUrl;
    @Column(columnDefinition = "TINYINT DEFAULT 0")
    private Integer isDeleted;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date modifyTime;
}
