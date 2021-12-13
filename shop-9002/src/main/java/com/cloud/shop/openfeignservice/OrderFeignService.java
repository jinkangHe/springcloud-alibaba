package com.cloud.shop.openfeignservice;

import com.cloud.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-9001")
public interface OrderFeignService {
    @GetMapping("api/order/getOrder")
    CommonResult getOrder();

}
