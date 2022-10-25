package com.yang.springbootmybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.springbootmybatisplus.entity.Employee;
import com.yang.springbootmybatisplus.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: 小强
 * @date: 2022/10/25 0025
 * @IDE: IntelliJ IDEA
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/list")
    public List<Employee> getList(){
        return employeeMapper.selectList(null);
    }

    @GetMapping("/{name}")
    public List<Employee> getListByName(@PathVariable String name) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("last_name",name);
        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        return employees;
    }
}
