package com.crime.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "WEAPON")
public class Weapon {
    @Id
    @Column(name = "WEAPON_USED_CD")
    private String weaponUsedCode;

    @Column(name = "WEAPON_DESC")
    private String weaponDescription;

    @OneToMany(mappedBy = "weapon")
    private Set<IncidentWeapon> incidentWeapons;

    // Getters and setters
}
