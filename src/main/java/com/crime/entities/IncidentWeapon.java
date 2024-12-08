package com.crime.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@IdClass(IncidentWeaponId.class)
@Table(name = "INCIDENT_WEAPON")
public class IncidentWeapon {

    @Id
    @ManyToOne
    @JoinColumn(name = "DR_NO")
    private Incident incident;

    @Id
    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "WEAPON_USED_CD")
    private Weapon weapon;


}
