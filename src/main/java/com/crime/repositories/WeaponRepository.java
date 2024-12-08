package com.crime.repositories;

import com.crime.entities.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponRepository extends JpaRepository<Weapon, String> {

}