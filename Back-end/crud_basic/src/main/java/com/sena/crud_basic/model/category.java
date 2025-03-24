package com.sena.crud_basic.model;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "category")
public class category {

    @Id
    @Column(name = "id_category", nullable = false)
    private int idCategory;

    @Column(name = "name_category", length = 200, nullable = false)
    private String nameCategory;


    @OneToMany
    @JoinColumn(name = "category", nullable = false)
    private List<service> services = new ArrayList<>();


    public int getIdCategory() {
        return idCategory;
    }


    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }


    public String getNameCategory() {
        return nameCategory;
    }


    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }


    public List<service> getServices() {
        return services;
    }


    public void setServices(List<service> services) {
        this.services = services;
    }


   
}
