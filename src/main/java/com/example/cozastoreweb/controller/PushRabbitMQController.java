package com.example.cozastoreweb.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class PushRabbitMQController {

    @Autowired
    private RabbitTemplate template;

    @GetMapping("")
    public ResponseEntity<?> getRabbit(String Message){

        template.convertAndSend("exchanges03","/root03",Message);

        return new ResponseEntity<>(" THÀNH CÔNG ", HttpStatus.OK);
    }
}
