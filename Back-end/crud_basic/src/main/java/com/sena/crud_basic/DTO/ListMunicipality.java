package com.sena.crud_basic.DTO;

public class ListMunicipality {
    private int idMunicipality;
    private String nameMunicipality;
    private int idDepartment;
    private String nameDepartament;
    public ListMunicipality() {
    }
    public ListMunicipality(int idMunicipality, String nameMunicipality, int idDepartment, String nameDepartament) {
        this.idMunicipality = idMunicipality;
        this.nameMunicipality = nameMunicipality;
        this.idDepartment = idDepartment;
        this.nameDepartament = nameDepartament;
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
    public int getIdDepartment() {
        return idDepartment;
    }
    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }
    public String getNameDepartament() {
        return nameDepartament;
    }
    public void setNameDepartament(String nameDepartament) {
        this.nameDepartament = nameDepartament;
    } 
}
