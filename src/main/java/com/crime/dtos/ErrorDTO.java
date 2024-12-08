package com.crime.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    String errorStatus;
    String errorDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDTO errorDTO = (ErrorDTO) o;
        return Objects.equals(errorStatus, errorDTO.errorStatus) && Objects.equals(errorDescription, errorDTO.errorDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorStatus, errorDescription);
    }
}
