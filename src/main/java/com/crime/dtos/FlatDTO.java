package com.crime.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FlatDTO {
    private String drNo;
    private LocalDateTime dateRptd;
    private LocalDateTime dateOcc;
    private String timeOcc;
    private Integer area;
    private String areaName;
    private Integer rptDistNo;
    private Integer part1Or2;
    private String crmCd;
    private String crmCdDesc;
    private String mocodes;
    private String victAge;
    private String victSex;
    private String victDescent;
    private String premisCd;
    private String premisDesc;
    private String weaponUsedCd;
    private String weaponDesc;
    private String status;
    private String statusDesc;
    private String crmCd1;
    private String crmCd2;
    private String crmCd3;
    private String crmCd4;
    private String location;
    private String crossStreet;
    private Float lat;
    private Float lon;

}
