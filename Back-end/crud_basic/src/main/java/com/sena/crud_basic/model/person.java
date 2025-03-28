package com.sena.crud_basic.model;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "person")
public class person {

    @Id
    @Column(name = "id_person", nullable = false)
    private int idPerson;

    @Column(name = "names_person", length = 100, nullable = false)
    private String namesPerson;

    @Column(name = "last_name_person", length = 100, nullable = false)
    private String lastNamePerson;

    @Column(name = "document_type_person", length = 10, nullable = false)
    private String documentTypePerson;

    @Column(name = "document_person", length = 10, nullable = false)
    private String documentPerson;

    @Column(name = "phone_person", length = 10, nullable = false)
    private String phonePerson;

    @Column(name = "email_person", length = 100, nullable = false)
    private String emailPerson;

    @Column(name = "address_person", length = 200, nullable = false)
    private String addressPerson;

    @ManyToOne
    @JoinColumn(name = "id_municipality", nullable = false)
    private municipality municipality;

    @OneToMany(mappedBy = "person")
    private List<clients> clients = new ArrayList<>();

    @OneToMany(mappedBy = "person")
    private List<employees> employees = new ArrayList<>();

    public person(int idPerson, String namesPerson, String lastNamePerson, String documentTypePerson,
            String documentPerson, String phonePerson, String emailPerson, String addressPerson,
            com.sena.crud_basic.model.municipality municipality, List<com.sena.crud_basic.model.clients> clients,
            List<com.sena.crud_basic.model.employees> employees) {
        this.idPerson = idPerson;
        this.namesPerson = namesPerson;
        this.lastNamePerson = lastNamePerson;
        this.documentTypePerson = documentTypePerson;
        this.documentPerson = documentPerson;
        this.phonePerson = phonePerson;
        this.emailPerson = emailPerson;
        this.addressPerson = addressPerson;
        this.municipality = municipality;
        this.clients = clients;
        this.employees = employees;
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

    public String getDocumentTypePerson() {
        return documentTypePerson;
    }

    public void setDocumentTypePerson(String documentTypePerson) {
        this.documentTypePerson = documentTypePerson;
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

    public municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(municipality municipality) {
        this.municipality = municipality;
    }

    public List<clients> getClients() {
        return clients;
    }

    public void setClients(List<clients> clients) {
        this.clients = clients;
    }

    public List<employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<employees> employees) {
        this.employees = employees;
    }

    
}
