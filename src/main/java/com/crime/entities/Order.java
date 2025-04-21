package com.crime.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ORDERS") // Avoid using 'ORDER' since it's a reserved SQL keyword
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "ORDER_ID", unique = true)
    private String orderId;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "ORDER_DATE")
    private String orderDate;

    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetails> orderDetails;
}
