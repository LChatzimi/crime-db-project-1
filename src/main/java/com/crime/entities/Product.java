package com.crime.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @Column(name = "PRODUCT_CODE", unique = true)
    private String productCode;


    @Column(name = "PRODUCT_NAME")
    private String productName;


    @Column(name = "PRICE")
    private Double price;
}
