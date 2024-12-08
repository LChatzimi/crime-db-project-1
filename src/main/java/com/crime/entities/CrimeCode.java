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
@Table(name = "CRIME_CODE")
public class CrimeCode {
    @Id
    @Column(name = "CRM_CD")
    private String crimeCode;

    @Column(name = "CRM_CD_DESC")
    private String crimeDescription;

    // Getters and setters
}
