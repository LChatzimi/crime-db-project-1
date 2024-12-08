package com.crime.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Query11ResponseDTO {
    private String areaName;
    private LocalDateTime dateOccurrence;
    private String crime1;
    private String crimeDescr1;
    private String crime2;
    private String crimeDescr2;
}