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
public class Query10ResponseDTO {
    private String name;
    private long longestGap;
    private LocalDateTime startTime ;
    private LocalDateTime endTime;
}