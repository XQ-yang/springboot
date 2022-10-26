package com.yang.springbootredis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class SpringbootRedisApplicationTests {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("设置操作")
    void testSet(){
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set("name","yangxiaoqiang");
    }

    @Test
    @DisplayName("获取操作")
    void testGet(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String name = operations.get("name");
        System.out.println(name);
    }

}
