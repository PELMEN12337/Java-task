package com.example.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord; // Убедитесь, что импорт правильный
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderStatusListener {

    @KafkaListener(topics = "order-status-topic", groupId = "order-service-group")
    public void listen(String message, ConsumerRecord<String, String> record) {
        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}",
                record.key(), record.partition(), record.topic(), record.timestamp());
    }
}
