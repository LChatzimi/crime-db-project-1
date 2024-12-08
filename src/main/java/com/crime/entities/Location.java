package com.crime.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@IdClass(LocationId.class)
@Table(name = "LOCATION")
public class Location {

    @Id
    @Column(name = "LOCATION")
    private String location;

    @Id
    @Column(name = "CROSS_STREET")
    private String crossStreet;

    @Id
    @JoinColumn(name = "AREA")
    @ManyToOne(cascade = CascadeType.ALL)
    private Area area;

    @Id
    @JoinColumn(name = "PREMIS_CD")
    @ManyToOne(cascade = CascadeType.ALL)
    private Premise premise;

    // Getters and setters
}
