package com.jkshop.mysqldemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jkshop.mysqldemo.util.IdWorker;

@MapperScan("com.jkshop.mysqldemo.dao")
@SpringBootApplication
public class MysqlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlDemoApplication.class, args);
	}

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
