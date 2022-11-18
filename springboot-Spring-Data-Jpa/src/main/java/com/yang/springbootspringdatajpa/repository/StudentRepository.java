package com.yang.springbootspringdatajpa.repository;

import com.yang.springbootspringdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: 小强
 * @date: 2022/11/18 0018
 * @IDE: IntelliJ IDEA
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
