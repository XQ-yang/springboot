package com.yang.springbootspringdatajpa.controller;

import com.yang.springbootspringdatajpa.entity.User;
import com.yang.springbootspringdatajpa.exception.ApiException;
import com.yang.springbootspringdatajpa.repository.UserRepository;
import com.yang.springbootspringdatajpa.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import static com.yang.springbootspringdatajpa.util.Result.success;

/**
 * @ClassName: UserController
 * @Author: XQ-Yang
 * @Date: 2022/11/16 0016
 * @Description:
 **/
@Api(tags = "用户Controller")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "根据id获取用户")
    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer id) {
        User user;
        try {
            user = userRepository.findById(id).get();
        } catch (Exception e) {
            throw new ApiException("304", "没有该对象");
        }
        return success(user);
    }


    @ApiOperation(value = "创建用户")
    @PostMapping
    public Result<User> createUser(@RequestBody User user) {

        return success(userRepository.save(user));
    }

    @ApiOperation(value = "更新用户")
    @PutMapping
    public Result<User> updateUser(@RequestBody User user) {
        return success(userRepository.save(user));
    }

    @ApiOperation(value = "根据id删除用户")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    @ApiOperation(value = "用户列表分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "property", value = "排序字段", paramType = "query", dataType = "String", defaultValue = "id"),
            @ApiImplicitParam(name = "direction", value = "排序规则", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页数", paramType = "query", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", paramType = "query", dataType = "Integer", defaultValue = "5"),
    })
    @GetMapping("/userListPage")
    public Result<Page<User>> userListPage(@RequestParam(defaultValue = "id") String property,
                                           @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                           @RequestParam(defaultValue = "0") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNum, pageSize, direction, property);
        Page<User> users;
        try {
            users = userRepository.findAll(pageable);
        } catch (Exception e) {
            throw new ApiException("305", "参数错误");
        }
        return success(users);
    }
}

