package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private static final String TOPIC = "order-topic";

    @PostMapping
    public String createOrder(@RequestBody Order order) {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setProduct(order.getProduct());
        orderEvent.setQuantity(order.getQuantity());
        kafkaTemplate.send(TOPIC, orderEvent);
        return "Order event sent to Kafka";
    }
}
