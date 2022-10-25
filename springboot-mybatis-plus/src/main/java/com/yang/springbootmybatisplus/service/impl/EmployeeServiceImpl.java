package com.yang.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.springbootmybatisplus.entity.Employee;
import com.yang.springbootmybatisplus.service.EmployeeService;
import com.yang.springbootmybatisplus.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author 小强
* @description 针对表【tbl_employee】的数据库操作Service实现
* @createDate 2022-10-25 22:04:35
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




