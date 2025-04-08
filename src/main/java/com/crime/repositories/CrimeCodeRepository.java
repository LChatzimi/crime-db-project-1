package com.crime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CrimeCodeRepository extends JpaRepository<CrimeCode, String> {

}