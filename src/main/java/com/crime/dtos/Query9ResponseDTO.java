package com.crime.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Query9ResponseDTO {
    private Double ageGroup;
    private String mostCommonWeapon;
    private Long weaponUsages;
}