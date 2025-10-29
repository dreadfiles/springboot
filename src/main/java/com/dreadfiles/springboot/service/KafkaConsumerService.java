package com.dreadfiles.springboot.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    /* Queue pattern example */
    @KafkaListener(topics = "example-topic", groupId = "example-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

    /* Topic pattern example */
    @KafkaListener(topics = "example-topic", groupId = "groupA")
    public void listenerA(String message) { System.out.println("Received message groupA: " + message); }

    @KafkaListener(topics = "example-topic", groupId = "groupB")
    public void listenerB(String message) { System.out.println("Received message groupB: " + message); }

}