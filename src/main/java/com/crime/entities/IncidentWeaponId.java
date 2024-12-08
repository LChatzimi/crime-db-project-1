package com.crime.entities;

import java.util.Objects;

public class IncidentWeaponId {

    private Incident incident;

    private Weapon weapon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncidentWeaponId that = (IncidentWeaponId) o;
        return incident.equals(that.incident) && weapon.equals(that.weapon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incident, weapon);
    }
}
