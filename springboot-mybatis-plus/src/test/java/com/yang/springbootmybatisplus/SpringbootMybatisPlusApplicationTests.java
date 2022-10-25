package com.yang.springbootmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper
                .ge("age",28)
                .or().like("last_name", "Bab");
        List<Employee> employees = employeeMapper.selectList(employeeQueryWrapper);
        employees.forEach(System.out::println);
    }

}
