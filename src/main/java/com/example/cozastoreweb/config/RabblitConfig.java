package com.example.cozastoreweb.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabblitConfig {

    @Bean
    public Queue hello(){
        return new Queue("cozastore03");
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("exchanges03");
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(hello()).to(topicExchange()).with("/root03");
    }
}
