package com.crime.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Query13ResponseDTO {
    private Long divisionOfRecords;
    private String areaName;
    private String crimeDescription;
    private String weaponDescription;
    private Long crimeCount;
}