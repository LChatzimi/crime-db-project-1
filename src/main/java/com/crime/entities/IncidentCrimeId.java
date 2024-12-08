package com.crime.entities;

import java.io.Serializable;
import java.util.Objects;


public class IncidentCrimeId implements Serializable {

    private Incident incident;

    private CrimeCode crimeCode;

    private Integer crimeRank;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncidentCrimeId that = (IncidentCrimeId) o;
        return Objects.equals(incident, that.incident) && Objects.equals(crimeCode, that.crimeCode) && Objects.equals(crimeRank, that.crimeRank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incident, crimeCode, crimeRank);
    }
}
