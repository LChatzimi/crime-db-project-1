package com.crime.services;

import com.crime.entities.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersistService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveBatch(IncidentService incidentService, CrimeCodeService crimeCodeService, WeaponService weaponService, List<Incident> incidents) {
        Set<Weapon> weapons = incidents.stream().map(Incident::getIncidentWeapons).flatMap(Collection::stream).map(IncidentWeapon::getWeapon).collect(Collectors.toSet());
        weaponService.saveAll(weapons);
        Set<CrimeCode> crimeCodes = incidents.stream().map(Incident::getIncidentCrimes).flatMap(Collection::stream).map(IncidentCrime::getCrimeCode).collect(Collectors.toSet());
        crimeCodeService.saveAll(crimeCodes);
        incidentService.saveAll(incidents);
        incidentService.flushClear();
    }
}
