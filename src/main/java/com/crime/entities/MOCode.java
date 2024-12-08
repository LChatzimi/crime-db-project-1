package com.crime.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "MO_CODE")
@IdClass(MOCodeId.class)
public class MOCode {
    @Id
    @ManyToOne
    @JoinColumn(name = "DR_NO")
    private Incident incident;

    @Id
    @Column(name = "MOCODES")
    private String mocodes;

    // Getters and setters
}
