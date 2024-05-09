package com.example.testtodoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class TestTodoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTodoAppApplication.class, args);
    }
}
