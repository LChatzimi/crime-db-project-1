package com.crime.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "PREMISE")
public class Premise {
    @Id
    @Column(name = "PREMIS_CD")
    private String premiseCode;

    @Column(name = "PREMIS_DESC")
    private String premiseDescription;


    // Getters and setters
}
