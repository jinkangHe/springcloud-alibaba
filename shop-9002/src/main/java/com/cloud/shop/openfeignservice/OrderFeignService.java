package com.cloud.shop.openfeignservice;

import com.cloud.common.OrderEntity;
import com.cloud.shop.balancer.TimeBalancerConfig;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-9001")
@LoadBalancerClient(name = "order-9001",configuration = TimeBalancerConfig.class)
public interface OrderFeignService {

    @GetMapping("api/order/getOrder")
    OrderEntity getOrder();

}
