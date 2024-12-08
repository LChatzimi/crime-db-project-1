package com.crime.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "VICTIM")
public class Victim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VICTIM_ID")
    private Integer victimId;

    @ManyToOne
    @JoinColumn(name = "DR_NO")
    private Incident incident;

    @Column(name = "VICT_AGE")
    private Integer victimAge;

    @Column(name = "VICT_SEX")
    private Character victimSex;

    @Column(name = "VICT_DESCENT")
    private Character victimDescent;

    // Getters and setters
}
