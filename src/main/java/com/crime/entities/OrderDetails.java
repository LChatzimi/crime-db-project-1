package com.crime.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_CODE")
    private Product product;

    @Column(name = "QUANTITY")
    private Integer quantity;
}
