package com.example.demo.listener;

import com.example.demo.model.OrderEvent;
import com.example.demo.model.OrderStatusEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderListener {

    private final KafkaTemplate<String, OrderStatusEvent> kafkaTemplate;
    private static final String STATUS_TOPIC = "order-status-topic";

    @KafkaListener(topics = "order-topic", groupId = "order-status-service-group")
    public void listen(OrderEvent event) {
        OrderStatusEvent statusEvent = new OrderStatusEvent();
        statusEvent.setStatus("CREATED");
        statusEvent.setDate(Instant.now());
        kafkaTemplate.send(STATUS_TOPIC, statusEvent);
    }
}
