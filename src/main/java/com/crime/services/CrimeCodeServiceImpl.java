package com.crime.services;

import com.crime.entities.CrimeCode;
import com.crime.repositories.CrimeCodeRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CrimeCodeServiceImpl implements CrimeCodeService {

    final CrimeCodeRepository crimeCodeRepository;

    public CrimeCodeServiceImpl(CrimeCodeRepository crimeCodeRepository) {
        this.crimeCodeRepository = crimeCodeRepository;
    }

    @Override
    public CrimeCode save(CrimeCode crimeCode) {
        return crimeCodeRepository.save(crimeCode);
    }

    @Override
    public void saveAll(Set<CrimeCode> crimeCodes) {
        crimeCodeRepository.saveAll(crimeCodes);
    }

    @Override
    public CrimeCode findOrNew(String crimeCode) {
        return crimeCodeRepository.findById(crimeCode).orElseGet(CrimeCode::new);
    }


}