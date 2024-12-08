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
public class Query6ResponseDTO {
    private String name;
    private LocalDateTime dateOccurrence;
    private long totalCrimes;

}