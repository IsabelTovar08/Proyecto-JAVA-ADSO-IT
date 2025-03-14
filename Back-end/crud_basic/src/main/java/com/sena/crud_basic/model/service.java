package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity(name = "service")

public class service {

    // @Id Indica que es la Primaty Key 
    @Id
    // @Colum indica que es una columna
    /*
     * name = Es el nombre de la columna en la base de datos
     * length = 10 es la longitud de la columna
     * nullable = fSi acepta nulo o no
     */
    @Column(name = "id_service", length = 10, nullable = false)
    private int id_service;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", length = 200, nullable = false)
    private String description;
}
