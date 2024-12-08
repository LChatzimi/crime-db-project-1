package com.crime.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Query3ResponseDTO {
    private String area;
    private String crimeCode;
    private long count;

}