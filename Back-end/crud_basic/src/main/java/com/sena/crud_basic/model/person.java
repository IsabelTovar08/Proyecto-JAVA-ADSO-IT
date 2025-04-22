package com.sena.crud_basic.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "person")
public class person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person", nullable = false)
    private int idPerson;

    @Column(name = "names_person", length = 100, nullable = false)
    private String namesPerson;

    @Column(name = "last_name_person", length = 100, nullable = false)
    private String lastNamePerson;


    @Column(name = "document_person", length = 100, nullable = false)
    private String documentPerson;

    @Column(name = "phone_person", length = 100, nullable = false)
    private String phonePerson;

    @Column(name = "email_person", length = 100, nullable = false)
    private String emailPerson;

    @Column(name = "address_person", length = 200, nullable = false)
    private String addressPerson;

    @ManyToOne
    @JoinColumn(name = "id_municipality", nullable = false)
    private municipality municipality;

    @Column(name = "creation_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "person")
    private List<clients> clients = new ArrayList<>();

    @OneToMany(mappedBy = "person")
    private List<employees> employees = new ArrayList<>();



    public person(int idPerson, String namesPerson, String lastNamePerson,
            String documentPerson, String phonePerson, String emailPerson, String addressPerson,
            com.sena.crud_basic.model.municipality municipality, LocalDateTime creationDate,
            List<com.sena.crud_basic.model.clients> clients, List<com.sena.crud_basic.model.employees> employees) {
        this.idPerson = idPerson;
        this.namesPerson = namesPerson;
        this.lastNamePerson = lastNamePerson;
        this.documentPerson = documentPerson;
        this.phonePerson = phonePerson;
        this.emailPerson = emailPerson;
        this.addressPerson = addressPerson;
        this.municipality = municipality;
        this.creationDate = creationDate;
        this.clients = clients;
        this.employees = employees;
    }

    public person() {
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

    public municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(municipality municipality) {
        this.municipality = municipality;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
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
