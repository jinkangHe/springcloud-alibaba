package com.cloud.order.controller;

import com.cloud.common.CommonResult;
import com.cloud.order.entity.OrderEntity;
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

    @GetMapping("getOrder")
    public CommonResult getOrder() {
        OrderEntity order = new OrderEntity();
        List<String> items = Arrays.asList("鼠标","键盘","可乐");
        order.setOrders(items);
        order.setOrderTime(LocalDateTime.now());
        order.setUuid(UUID.randomUUID().toString());
        return CommonResult.success(order);
    }
}
