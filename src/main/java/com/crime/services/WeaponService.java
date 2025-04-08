package com.crime.services;

import java.util.Set;

public interface WeaponService {


    Weapon save(Weapon weapon);


    void saveAll(Set<Weapon> weapons);

    Weapon findOrNew(String weaponUsedCode);
}