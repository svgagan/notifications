package com.example.notifications.service.kafka;

import com.example.notifications.model.Customer;
import com.example.notifications.utility.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @KafkaListener(containerFactory = "kafkaListenerContainerFactory",
            topics = "${kafka.topic.string-demo.name}",
            groupId = "${kafka.topic.string-demo.groupId}")
    public void consume(String message) {
        log.info(String.format("$$$$ => Consumed message: %s", message));
    }

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.json-demo.name}",
            groupId = "${kafka.topic.json-demo.groupId}")
    public void consumeCustomerData(Customer customer) {
        log.info("Consumed Message: {}, {}", customer.getCustomerId(), customer.getCustomerName());
        EmailSender.sendEmail("chicago@egen.solutions", customer.getEmail(), customer.getMessage());
    }
}
