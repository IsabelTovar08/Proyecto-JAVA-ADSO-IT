package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "municipality")
public class municipality {

    @Id
    @Column(name = "id_municipality", nullable = false)
    private int idMunicipality;

    @Column(name = "name_municipality", length = 200, nullable = false)
    private String nameMunicipality;

    @ManyToOne
    @JoinColumn(name = "id_department", nullable = false)
    private department department;

    @OneToMany(mappedBy = "municipality")
    private List<branch> branches = new ArrayList<>();

    public municipality(int idMunicipality, String nameMunicipality, com.sena.crud_basic.model.department department,
            List<branch> branches) {
        this.idMunicipality = idMunicipality;
        this.nameMunicipality = nameMunicipality;
        this.department = department;
        this.branches = branches;
    }

    public int getIdMunicipality() {
        return idMunicipality;
    }

    public void setIdMunicipality(int idMunicipality) {
        this.idMunicipality = idMunicipality;
    }

    public String getNameMunicipality() {
        return nameMunicipality;
    }

    public void setNameMunicipality(String nameMunicipality) {
        this.nameMunicipality = nameMunicipality;
    }

    public department getDepartment() {
        return department;
    }

    public void setDepartment(department department) {
        this.department = department;
    }

    public List<branch> getBranches() {
        return branches;
    }

    public void setBranches(List<branch> branches) {
        this.branches = branches;
    }

   
}
