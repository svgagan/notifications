package com.example.notifications.controller;

import com.example.notifications.model.Customer;
import com.example.notifications.service.kafka.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@Slf4j
@RequestMapping("/kafka")
public class KafkaController {

    private final ProducerService producerService;

    public KafkaController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestParam String message) {
        log.info("Message Received in KafkaController: {}",message);
        producerService.sendMessage(message);
        return "Message Received";
    }

    @PostMapping(value = "/publish/customer")
    public String sendMessageToKafkaTopic(@RequestBody Customer customer) {
        log.info("Message Received in KafkaController: {}",customer);
        String order = customer.getOrderId();
        customer.setOrderId(order.concat(Long.toString(Instant.now().getEpochSecond())));
        producerService.sendCustomerDataMessage(customer);
        return "Message Received";
    }
}
