package com.crime.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@IdClass(IncidentCrimeId.class)
@Table(name = "INCIDENT_CRIME")
public class IncidentCrime {

    @Id
    @ManyToOne
    @JoinColumn(name = "DR_NO")
    private Incident incident;

    @Id
    @JoinColumn(name = "CRM_CD")
    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private CrimeCode crimeCode;

    @Id
    @Column(name = "CRIME_RANK")
    private Integer crimeRank;

}
