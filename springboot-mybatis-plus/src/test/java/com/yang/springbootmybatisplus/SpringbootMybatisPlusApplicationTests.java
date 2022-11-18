package com.yang.springbootmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yang.springbootmybatisplus.entity.Employee;
import com.yang.springbootmybatisplus.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatisPlusApplicationTests {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void contextLoads() {
        List<Employee> employees = employeeMapper.selectList(null);
        employees.forEach(System.out::println);
    }

    @Test
    void selectById(){
        Employee employee = employeeMapper.selectById(2);
        System.out.println(employee);
    }

    @Test
    void selectByLike(){
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Employee::getLastName,"Bab");
        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        employees.forEach(System.out::println);
    }

}
