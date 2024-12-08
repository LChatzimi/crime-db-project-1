package com.crime.entities;

import java.io.Serializable;
import java.util.Objects;

public class MOCodeId implements Serializable {

    private Incident incident;

    private String mocodes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MOCodeId moCodeId = (MOCodeId) o;
        return Objects.equals(incident, moCodeId.incident) && Objects.equals(mocodes, moCodeId.mocodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incident, mocodes);
    }
}
