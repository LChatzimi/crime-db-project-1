package com.crime.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_CODE")
    private Product product;

    @Column(name = "QUANTITY")
    private Integer quantity;
}
