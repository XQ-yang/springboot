package com.yang.springbootspringdatajpa.repository;

import com.yang.springbootspringdatajpa.entity.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: 小强
 * @date: 2022/11/18 0018
 * @IDE: IntelliJ IDEA
 */
public interface FileRepository extends JpaRepository<FileModel, Integer> {
    /**
     * 根据md5获取
     * @param md5
     * @return
     */
    List<FileModel> findByMd5(String md5);
}
