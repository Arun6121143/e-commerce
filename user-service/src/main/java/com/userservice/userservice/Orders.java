package com.userservice.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private UUID orderId;
    private Timestamp orderDate;
    private UUID productId;
    private UUID userId;
}
