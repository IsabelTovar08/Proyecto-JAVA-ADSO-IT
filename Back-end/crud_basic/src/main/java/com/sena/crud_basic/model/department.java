package com.sena.crud_basic.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "department")
public class department {

    @Id
    @Column(name = "id_department", nullable = false)
    private int idDepartment;

    @Column(name = "name_department", length = 200, nullable = false)
    private String nameDepartment;

    @OneToMany(mappedBy = "department")
    private List<municipality> municipalities = new ArrayList<>();

    public department(int idDepartment, String nameDepartment, List<municipality> municipalities) {
        this.idDepartment = idDepartment;
        this.nameDepartment = nameDepartment;
        this.municipalities = municipalities;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public List<municipality> getMunicipalities() {
        return municipalities;
    }

    public void setMunicipalities(List<municipality> municipalities) {
        this.municipalities = municipalities;
    }

   
}
