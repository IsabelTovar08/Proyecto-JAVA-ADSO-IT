package com.sena.crud_basic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity(name = "branch")
public class branch {
    @Id
    @Column(name = "id_branch", nullable = false)
    private int idBranch;

    @Column(name = "name_branch", length = 200, nullable = false)
    private String nameBranch;

    @ManyToOne
    @JoinColumn(name = "id_municipality", nullable = false)
    private municipality municipality;

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }

    public String getNameBranch() {
        return nameBranch;
    }

    public void setNameBranch(String nameBranch) {
        this.nameBranch = nameBranch;
    }

    public municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(municipality municipality) {
        this.municipality = municipality;
    }

   
}
