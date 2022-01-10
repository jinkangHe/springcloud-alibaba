package com.cloud.common;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderEntity {
    private String uuid;
    private List<String> orders;
    private LocalDateTime orderTime;
    private int port;
}
