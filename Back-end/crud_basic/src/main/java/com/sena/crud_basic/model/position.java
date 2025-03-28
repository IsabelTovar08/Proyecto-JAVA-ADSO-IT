package com.sena.crud_basic.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "position")

public class position {
    @Id
    @Column(name = "id_position", nullable = false)
    private int idPosition;

    @Column(name = "name_position", length = 200, nullable = false)
    private String namePosition;

    @OneToMany(mappedBy = "position")
    private List<employees> employees = new ArrayList<>();

    public position(int idPosition, String namePosition, List<com.sena.crud_basic.model.employees> employees) {
        this.idPosition = idPosition;
        this.namePosition = namePosition;
        this.employees = employees;
    }

    public int getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(int idPosition) {
        this.idPosition = idPosition;
    }

    public String getNamePosition() {
        return namePosition;
    }

    public void setNamePosition(String namePosition) {
        this.namePosition = namePosition;
    }

    public List<employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<employees> employees) {
        this.employees = employees;
    }

    
}
