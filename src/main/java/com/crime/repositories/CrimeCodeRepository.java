package com.crime.repositories;

import com.crime.entities.CrimeCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrimeCodeRepository extends JpaRepository<CrimeCode, String> {

}