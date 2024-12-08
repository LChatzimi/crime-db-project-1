package com.crime.entities;

import java.util.Objects;

public class LocationId {

    private String location;

    private String crossStreet;

    private Area area;

    private Premise premise;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationId that = (LocationId) o;
        return location.equals(that.location) && crossStreet.equals(that.crossStreet) && area.equals(that.area) && premise.equals(that.premise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, crossStreet, area, premise);
    }
}
