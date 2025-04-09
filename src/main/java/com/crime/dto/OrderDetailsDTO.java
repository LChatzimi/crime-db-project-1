package com.crime.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDTO {
    private ProductDTO product;
    private Integer quantity;
}
