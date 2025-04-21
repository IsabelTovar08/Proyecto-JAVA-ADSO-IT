package com.sena.crud_basic.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.category;

public interface ICategory extends JpaRepository<category, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar categorías por nombre o descripción
    // List<category> findByName(String name);

}
