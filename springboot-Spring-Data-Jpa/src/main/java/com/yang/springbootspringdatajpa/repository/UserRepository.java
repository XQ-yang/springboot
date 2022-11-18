package com.yang.springbootspringdatajpa.repository;

import com.yang.springbootspringdatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: UserRepository
 * @Author: XQ-Yang
 * @Date: 2022/11/16 0016
 * @Description:
 **/
public interface UserRepository extends JpaRepository<User, Integer> {

}
