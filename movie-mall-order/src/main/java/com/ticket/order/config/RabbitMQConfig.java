package com.ticket.order.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String ORDER_CREATE_QUEUE = "order.create.queue";
    public static final String ORDER_CREATE_DLQ = "order.create.dlq";
    public static final String ORDER_CREATE_ROUTING_KEY = "order.create";

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE, true, false);
    }

    @Bean
    public Queue orderCreateQueue() {
        Map<String, Object> args = new HashMap<>();
        // 死信交换机
        args.put("x-dead-letter-exchange", ORDER_EXCHANGE);
        // 死信路由键
        args.put("x-dead-letter-routing-key", ORDER_CREATE_ROUTING_KEY + ".dlq");
        return new Queue(ORDER_CREATE_QUEUE, true, false, false, args);
    }

    @Bean
    public Queue orderCreateDlq() {
        Map<String, Object> args = new HashMap<>();
        // DLQ 的消息过期后重新投递到原队列，实现重试
        args.put("x-dead-letter-exchange", ORDER_EXCHANGE);
        args.put("x-dead-letter-routing-key", ORDER_CREATE_ROUTING_KEY);
        args.put("x-message-ttl", 30000);
        return new Queue(ORDER_CREATE_DLQ, true, false, false, args);
    }

    @Bean
    public Binding orderCreateBinding(Queue orderCreateQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderCreateQueue)
                .to(orderExchange)
                .with(ORDER_CREATE_ROUTING_KEY);
    }

    @Bean
    public Binding orderCreateDlqBinding(Queue orderCreateDlq, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderCreateDlq)
                .to(orderExchange)
                .with(ORDER_CREATE_ROUTING_KEY + ".dlq");
    }
}
