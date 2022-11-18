package com.yang.springbootspringdatajpa;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SpringbootSpringDataJpaApplicationTests {
    @BeforeAll
    static void beforeAll() {
        System.err.println("====before all====");
    }

    @BeforeEach
    void beforeEach() {
        System.err.println("====before each====");
    }

    @Test
    void test1() {
        System.err.println("====test1====");
    }

    @Test
    void test2() {
        System.err.println("====test2====");
    }

    @AfterAll
    static void afterAll() {
        System.err.println("====after all====");
    }

    @AfterEach
    void afterEach() {
        System.err.println("====after each====");
    }

}
