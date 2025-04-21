package com.sena.crud_basic.DTO.requestDTO;

import java.time.LocalDateTime;

public class requestPerson {
    private int idPerson;
    private String namesPerson;
    private String lastNamePerson;
    private String documentPerson;
    private String phonePerson;
    private String emailPerson;
    private String addressPerson;
    private int idMunicipality;
    private String municipality_name;
    private LocalDateTime creationDate;
    public requestPerson() {
    }
    public requestPerson(int idPerson, String namesPerson, String lastNamePerson, String documentPerson,
            String phonePerson, String emailPerson, String addressPerson, int idMunicipality, String municipality_name,
            LocalDateTime creationDate) {
        this.idPerson = idPerson;
        this.namesPerson = namesPerson;
        this.lastNamePerson = lastNamePerson;
        this.documentPerson = documentPerson;
        this.phonePerson = phonePerson;
        this.emailPerson = emailPerson;
        this.addressPerson = addressPerson;
        this.idMunicipality = idMunicipality;
        this.municipality_name = municipality_name;
        this.creationDate = creationDate;
    }
    public int getIdPerson() {
        return idPerson;
    }
    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }
    public String getNamesPerson() {
        return namesPerson;
    }
    public void setNamesPerson(String namesPerson) {
        this.namesPerson = namesPerson;
    }
    public String getLastNamePerson() {
        return lastNamePerson;
    }
    public void setLastNamePerson(String lastNamePerson) {
        this.lastNamePerson = lastNamePerson;
    }
    public String getDocumentPerson() {
        return documentPerson;
    }
    public void setDocumentPerson(String documentPerson) {
        this.documentPerson = documentPerson;
    }
    public String getPhonePerson() {
        return phonePerson;
    }
    public void setPhonePerson(String phonePerson) {
        this.phonePerson = phonePerson;
    }
    public String getEmailPerson() {
        return emailPerson;
    }
    public void setEmailPerson(String emailPerson) {
        this.emailPerson = emailPerson;
    }
    public String getAddressPerson() {
        return addressPerson;
    }
    public void setAddressPerson(String addressPerson) {
        this.addressPerson = addressPerson;
    }
    public int getIdMunicipality() {
        return idMunicipality;
    }
    public void setIdMunicipality(int idMunicipality) {
        this.idMunicipality = idMunicipality;
    }
    public String getMunicipality_name() {
        return municipality_name;
    }
    public void setMunicipality_name(String municipality_name) {
        this.municipality_name = municipality_name;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    
}
