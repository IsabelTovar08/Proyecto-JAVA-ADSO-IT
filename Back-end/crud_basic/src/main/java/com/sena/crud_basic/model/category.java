package com.sena.crud_basic.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity(name = "category")
public class category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", nullable = false)
    private int idCategory;

    @Column(name = "name_category", length = 200, nullable = false)
    private String nameCategory;

    @OneToMany(mappedBy = "category")
    // @JsonManagedReference
    // @JsonBackReference
    @JsonIgnoreProperties("category")
    private List<service> services = new ArrayList<>();

    public category() {
    }

    public category(int idCategory, String nameCategory,
            List<service> services) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.services = services;
    }

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
