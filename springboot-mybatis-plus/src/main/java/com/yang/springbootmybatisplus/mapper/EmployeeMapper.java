package com.yang.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.springbootmybatisplus.entity.Employee;
import org.springframework.stereotype.Repository;

/**
* @author 小强
* @description 针对表【tbl_employee】的数据库操作Mapper
* @createDate 2022-10-25 22:04:35
* @Entity com.yang.springbootmybatisplus.entity.Employee
*/
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

}




