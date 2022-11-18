package com.yang.springbootspringdatajpa.controller;

import com.yang.springbootspringdatajpa.entity.Student;
import com.yang.springbootspringdatajpa.exception.ApiException;
import com.yang.springbootspringdatajpa.repository.StudentRepository;
import com.yang.springbootspringdatajpa.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static com.yang.springbootspringdatajpa.util.Result.success;

/**
 * @author: 小强
 * @date: 2022/11/18 0018
 * @IDE: IntelliJ IDEA
 */
@Api(tags = "学生Controller")
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @ApiOperation(value = "根据id获取对象")
    @GetMapping("/{id}")
    public Result<Student> getStudentById(@PathVariable("id") Integer id) {
        Student student;
        try {
            student = studentRepository.findById(id).get();
        } catch (Exception e) {
            throw new ApiException("304", "没有该对象");
        }

        return success(student);
    }

    @ApiOperation("分页获取学生信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页数", paramType = "query", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", paramType = "query", dataType = "Integer", defaultValue = "10"),
    })
    @GetMapping("/studentPageList")
    public Result<Page<Student>> getListPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Student> students;
        try {
            students = studentRepository.findAll(pageable);
        } catch (Exception e) {
            throw new ApiException("300", "系统错误");
        }

        return success(students);
    }

    @ApiOperation("新增学生对象")
    @PostMapping()
    public Result<Student> save(@RequestBody Student student) {
        return success(studentRepository.save(student));
    }

    @ApiOperation(value = "编辑学生信息")
    @PutMapping()
    public Result<Student> edit(@RequestBody Student student) {
        return success(studentRepository.save(student));
    }

    @ApiOperation("删除学生")
    @ApiImplicitParam(name = "id", paramType = "path", dataType = "Integer", required = true, example = "1")
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentRepository.deleteById(id);
    }
}
