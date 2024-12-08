package com.crime.services;

import com.crime.dtos.FlatDTO;
import com.crime.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class DataImportService {

    @Transactional
    public void importData(List<FlatDTO> flatDTOS, IncidentService incidentService, CrimeCodeService crimeCodeService, WeaponService weaponService , PersistService persistService) {
        System.out.println(flatDTOS.size());
        LocalDateTime start = LocalDateTime.now();
        List<Incident> incidents = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger();
        int batchSize = 5000;
        flatDTOS.forEach(flatDTO -> {
            Area area = new Area();
            area.setArea(flatDTO.getArea());
            area.setAreaName(flatDTO.getAreaName());

            Premise premise = new Premise();
            premise.setPremiseCode(flatDTO.getPremisCd());
            premise.setPremiseDescription(flatDTO.getPremisDesc());

//            Location location = new Location();
//            location.setLocation(flatDTO.getLocation());
//            location.setCrossStreet(flatDTO.getCrossStreet());
//            location.setArea(area);
//            location.setPremise(premise);

            Incident incident = new Incident();
            incident.setDrNo(flatDTO.getDrNo());
            incident.setDateReported(flatDTO.getDateRptd());
            incident.setDateOccurred(flatDTO.getDateOcc());
            incident.setTimeOccurred(flatDTO.getTimeOcc());
            incident.setReportDistrictNo(flatDTO.getRptDistNo());
            incident.setStatus(flatDTO.getStatus());
            incident.setStatusDescription(flatDTO.getStatusDesc());
            incident.setLatitude(flatDTO.getLat());
            incident.setLongitude(flatDTO.getLon());
//            incident.setLocation(location);
            incident.setLocation(flatDTO.getLocation());
            incident.setCrossStreet(flatDTO.getCrossStreet());
            incident.setArea(area);
            incident.setPremise(premise);


            Victim victim = new Victim();
            victim.setVictimAge(flatDTO.getVictAge() != null ? Integer.parseInt(flatDTO.getVictAge()) : null);
            victim.setVictimSex(flatDTO.getVictSex() != null && !flatDTO.getVictSex().isEmpty() ? flatDTO.getVictSex().charAt(0) : null);
            victim.setVictimDescent(flatDTO.getVictDescent() != null && !flatDTO.getVictDescent().isEmpty() ? flatDTO.getVictDescent().charAt(0) : null);
            victim.setIncident(incident);
            incident.getVictims().add(victim);

            List<String> moCodesList = Arrays.asList(flatDTO.getMocodes().split(" "));
            moCodesList.stream().filter(mo -> mo != null && !mo.isEmpty())
                    .forEach(moCodeValue -> {
                        MOCode moCode = new MOCode();
                        moCode.setIncident(incident);
                        moCode.setMocodes(moCodeValue);
                        incident.getMoCodes().add(moCode);
                    });

            IncidentCrime incidentCrime = new IncidentCrime();


            CrimeCode crimeCode = crimeCodeService.findOrNew(flatDTO.getCrmCd());
            crimeCode.setCrimeCode(flatDTO.getCrmCd());
            crimeCode.setCrimeDescription(flatDTO.getCrmCdDesc());
            incidentCrime.setCrimeCode(crimeCode);
            incidentCrime.setIncident(incident);

            if (!flatDTO.getCrmCd().equals(flatDTO.getCrmCd1())){
                IncidentCrime incidentCrime1 = new IncidentCrime();
                CrimeCode crimeCode1 =  crimeCodeService.findOrNew(flatDTO.getCrmCd1());
                crimeCode1.setCrimeCode(flatDTO.getCrmCd1());
                incidentCrime1.setCrimeCode(crimeCode1);
                incidentCrime1.setCrimeRank(1);
                incidentCrime1.setIncident(incident);
                incidentCrime1.setIncident(incident);
                incident.getIncidentCrimes().add(incidentCrime1);
            }else {
                incidentCrime.setCrimeRank(1);
                incident.getIncidentCrimes().add(incidentCrime);
            }

            if (flatDTO.getCrmCd2() != null && !flatDTO.getCrmCd2().isEmpty()) {
                if (!flatDTO.getCrmCd().equals(flatDTO.getCrmCd2())) {
                    IncidentCrime incidentCrime2 = new IncidentCrime();
                    CrimeCode crimeCode2 = crimeCodeService.findOrNew(flatDTO.getCrmCd2());
                    crimeCode2.setCrimeCode(flatDTO.getCrmCd2());
                    incidentCrime2.setCrimeCode(crimeCode2);
                    incidentCrime2.setCrimeRank(2);
                    incidentCrime2.setIncident(incident);
                    incident.getIncidentCrimes().add(incidentCrime2);
                }else {
                    incidentCrime.setCrimeRank(2);
                    incident.getIncidentCrimes().add(incidentCrime);
                }
            }


            if (flatDTO.getCrmCd3() != null && !flatDTO.getCrmCd3().isEmpty()) {
                if (!flatDTO.getCrmCd().equals(flatDTO.getCrmCd3())) {
                    IncidentCrime incidentCrime3 = new IncidentCrime();
                    CrimeCode crimeCode3 = crimeCodeService.findOrNew(flatDTO.getCrmCd3());
                    crimeCode3.setCrimeCode(flatDTO.getCrmCd3());
                    incidentCrime3.setCrimeCode(crimeCode3);
                    incidentCrime3.setCrimeRank(3);
                    incidentCrime3.setIncident(incident);
                    incident.getIncidentCrimes().add(incidentCrime3);
                } else {
                    incidentCrime.setCrimeRank(3);
                    incident.getIncidentCrimes().add(incidentCrime);
                }
            }

            if (flatDTO.getCrmCd4() != null && !flatDTO.getCrmCd4().isEmpty()) {
                if (!flatDTO.getCrmCd().equals(flatDTO.getCrmCd2())) {
                    IncidentCrime incidentCrime4 = new IncidentCrime();
                    CrimeCode crimeCode4 = crimeCodeService.findOrNew(flatDTO.getCrmCd4());
                    crimeCode4.setCrimeCode(flatDTO.getCrmCd4());
                    incidentCrime4.setCrimeCode(crimeCode4);
                    incidentCrime4.setCrimeRank(4);
                    incidentCrime4.setIncident(incident);
                    incident.getIncidentCrimes().add(incidentCrime4);
                } else {
                    incidentCrime.setCrimeRank(4);
                    incident.getIncidentCrimes().add(incidentCrime);
                }
            }

            Set<IncidentWeapon> incidentWeapons = new HashSet<>();
            Weapon weapon = new Weapon();
            weapon.setWeaponUsedCode(flatDTO.getWeaponUsedCd()!=null && !flatDTO.getWeaponUsedCd().isEmpty() ? flatDTO.getWeaponUsedCd() : "500" );
            weapon.setWeaponDescription(flatDTO.getWeaponDesc() != null && !flatDTO.getWeaponDesc().isEmpty() ? flatDTO.getWeaponDesc() : "UNKNOWN WEAPON/OTHER WEAPON");
            IncidentWeapon incidentWeapon = new IncidentWeapon();
            incidentWeapon.setWeapon(weapon);
            incidentWeapon.setIncident(incident);
            incidentWeapons.add(incidentWeapon);
            incident.setIncidentWeapons(incidentWeapons);

            incidents.add(incident);
            counter.getAndIncrement();
            if (counter.get() % batchSize == 0){
                LocalDateTime beforeSave = LocalDateTime.now();
                System.out.println("Time taken to load data "+batchSize+" : " + Duration.between(start,beforeSave).getSeconds()  + " Seconds" );
                persistService.saveBatch(incidentService, crimeCodeService, weaponService, incidents);
                incidents.clear();
                LocalDateTime end = LocalDateTime.now();
                System.out.println("Time taken to save data "+batchSize+" : " + Duration.between(start,end).getSeconds()  + " Seconds" );
            }
        });

        if (!incidents.isEmpty()) {
            persistService.saveBatch(incidentService, crimeCodeService, weaponService, incidents);
        }

        LocalDateTime end = LocalDateTime.now();
        System.out.println("Time taken to load data:  " + Duration.between(start,end).getSeconds()  + " Seconds" );
        System.out.println("Data loaded successfully.");
    }


//990293
}
