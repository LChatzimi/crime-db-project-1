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
@Table(name = "AREA")
public class Area {
    @Id
    @Column(name = "AREA")
    private Integer area;

    @Column(name = "AREA_NAME")
    private String areaName;


}
