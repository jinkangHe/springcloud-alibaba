package com.cloud.order.controller;

import com.cloud.common.CommonResult;
import com.cloud.common.OrderEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Value("${server.port}")
    private int port;

    @GetMapping("getOrder")
    public OrderEntity getOrder() {
        OrderEntity order = new OrderEntity();
        List<String> items = Arrays.asList("鼠标","键盘","可乐");
        order.setOrders(items);
        order.setOrderTime(LocalDateTime.now());
        order.setUuid(UUID.randomUUID().toString());
        order.setPort(port);
        return order;
    }
}
