package com.hzs.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 220419
 * @description
 * @date 2026/9/21
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.hzs"})
@MapperScan(basePackages = {"com.hzs.**.dao.mapper"})
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
        System.out.println("服务启动！");
    }
}
