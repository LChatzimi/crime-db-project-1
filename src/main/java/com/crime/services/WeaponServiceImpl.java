package com.crime.services;

import com.crime.entities.Weapon;
import com.crime.repositories.WeaponRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class WeaponServiceImpl implements WeaponService {

    final WeaponRepository weaponRepository;

    public WeaponServiceImpl(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @Override
    public Weapon save(Weapon weapon) {
        return weaponRepository.save(weapon);
    }

    @Override
    public void saveAll(Set<Weapon> weapons) {
        weaponRepository.saveAll(weapons);
    }

    @Override
    public Weapon findOrNew(String weaponUsedCode) {
        return weaponRepository.findById(weaponUsedCode).orElseGet(Weapon::new);
    }


}