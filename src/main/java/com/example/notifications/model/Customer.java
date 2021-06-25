package com.example.notifications.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private String customerId;
    private String customerName;
    private String email;
    private String phone;
    private String orderId;
    private String message;
}
