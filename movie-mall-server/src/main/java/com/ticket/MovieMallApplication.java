package com.ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(exposeProxy = true)
@EnableRabbit
@MapperScan({"com.ticket.film.mapper", "com.ticket.order.mapper", "com.ticket.user.mapper"})
@SpringBootApplication
public class MovieMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieMallApplication.class, args);
    }

}
