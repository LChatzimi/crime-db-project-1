package com.crime.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private String orderId;
    private String customerName;
    private String orderDate;
    private Double totalAmount;
    private List<OrderDetailsDTO> orderDetails;
}
