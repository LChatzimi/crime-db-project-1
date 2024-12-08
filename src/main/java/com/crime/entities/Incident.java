package com.crime.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "INCIDENT")
public class Incident {
    @Id
    @Column(name = "DR_NO")
    private String drNo;

    @Column(name = "DATE_RPTD")
    private LocalDateTime dateReported;

    @Column(name = "DATE_OCC")
    private LocalDateTime dateOccurred;

    @Column(name = "TIME_OCC")
    private String timeOccurred;

    @Column(name = "RPT_DIST_NO")
    private Integer reportDistrictNo;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "STATUS_DESC")
    private String statusDescription;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumns({
//            @JoinColumn(name = "LOCATION", referencedColumnName = "LOCATION"),
//            @JoinColumn(name = "CROSS_STREET", referencedColumnName = "CROSS_STREET"),
//            @JoinColumn(name = "AREA", referencedColumnName = "AREA"),
//            @JoinColumn(name = "PREMIS_CD", referencedColumnName = "PREMIS_CD")
//    })
//    private Location location;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "CROSS_STREET")
    private String crossStreet;

    @JoinColumn(name = "AREA")
    @ManyToOne(cascade = CascadeType.ALL)
    private Area area;

    @JoinColumn(name = "PREMIS_CD")
    @ManyToOne(cascade = CascadeType.ALL)
    private Premise premise;

    @Column(name = "LAT")
    private Float latitude;

    @Column(name = "LON")
    private Float longitude;

    @OneToMany(mappedBy = "incident" , cascade = CascadeType.ALL)
    private Set<IncidentCrime> incidentCrimes  = new HashSet<>();

    @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL)
    private Set<Victim> victims = new HashSet<>();

    @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL)
    private Set<IncidentWeapon> incidentWeapons = new HashSet<>();

    @OneToMany(mappedBy = "incident" , cascade = CascadeType.ALL)
    private Set<MOCode> moCodes = new HashSet<>();

    // Getters and setters
}
