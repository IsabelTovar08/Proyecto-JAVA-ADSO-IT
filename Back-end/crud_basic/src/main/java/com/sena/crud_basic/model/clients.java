package com.sena.crud_basic.model;

import jakarta.persistence.Entity;

import java.security.Timestamp;

import jakarta.persistence.*;

@Entity(name = "clients")
public class clients {
    @Id
    @Column(name = "id_client", nullable = false)
    private int idClient;

    @ManyToOne
    @JoinColumn(name = "id_person", nullable = false)
    private person person;

    @Column(name = "registration_date_client", nullable = false)
    private Timestamp registrationDateClient;

    public clients(int idClient, com.sena.crud_basic.model.person person, Timestamp registrationDateClient) {
        this.idClient = idClient;
        this.person = person;
        this.registrationDateClient = registrationDateClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public person getPerson() {
        return person;
    }

    public void setPerson(person person) {
        this.person = person;
    }

    public Timestamp getRegistrationDateClient() {
        return registrationDateClient;
    }

    public void setRegistrationDateClient(Timestamp registrationDateClient) {
        this.registrationDateClient = registrationDateClient;
    }

    
}
