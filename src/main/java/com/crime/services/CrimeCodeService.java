package com.crime.services;

import java.util.Set;

public interface CrimeCodeService {


    CrimeCode save(CrimeCode crimeCode);


    void saveAll(Set<CrimeCode> crimeCodes);

    CrimeCode findOrNew(String crimeCode);
}