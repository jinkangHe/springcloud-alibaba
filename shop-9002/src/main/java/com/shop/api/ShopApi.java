package com.shop.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/shop")
@RefreshScope
public class ShopApi {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("serverName")
    public String serverName() {
        return configInfo;
    }

}
