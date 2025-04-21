package com.sena.crud_basic.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.municipality;

public interface IMunicipality extends JpaRepository<municipality, Integer> {

}
