package com.sena.crud_basic.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ListPersonDTO {

    private int idPerson;
    private String namesPerson;
    private String lastNamePerson;
    private String documentPerson;
    private String phonePerson;
    private String emailPerson;
    private String addressPerson;
    private int idMunicipality;
    private LocalDateTime creationDate;
  
    public ListPersonDTO() {
    }
    public ListPersonDTO(int idPerson, String namesPerson, String lastNamePerson, String documentPerson,
            String phonePerson, String emailPerson, String addressPerson, int idMunicipality, LocalDateTime creationDate) {
        this.idPerson = idPerson;
        this.namesPerson = namesPerson;
        this.lastNamePerson = lastNamePerson;
        this.documentPerson = documentPerson;
        this.phonePerson = phonePerson;
        this.emailPerson = emailPerson;
        this.addressPerson = addressPerson;
        this.idMunicipality = idMunicipality;
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
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
 
    
}
