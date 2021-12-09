package com.cloud.shop.api;

import com.cloud.common.CommonResult;
import com.cloud.shop.openfeignservice.OrderFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/shop")
@RefreshScope
@Slf4j
public class ShopApi {

    @Value("${config.info}")
    private String configInfo;

    @Autowired
    private OrderFeignService orderFeignService;

    @GetMapping("serverName")
    public CommonResult serverName() {

        return CommonResult.success(configInfo);
    }

    @GetMapping("shop")
    public CommonResult shop() {
        log.info("获取到请求");

        log.info("远程服务调用.....");
        CommonResult order = orderFeignService.getOrder();
        Object data = order.getData();
        log.info("调用结果：{}",data);
        return CommonResult.success();
    }


}
