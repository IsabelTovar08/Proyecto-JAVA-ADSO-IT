package com.sena.crud_basic.model;


import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity(name = "clients")
public class clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client", nullable = false)
    private int idClient;

    @ManyToOne
    @JoinColumn(name = "id_person", nullable = false)
    private person person;

    @Column(name = "registration_date_client", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime  registrationDateClient;

    public clients(int idClient, com.sena.crud_basic.model.person person,
            LocalDateTime  registrationDateClient) {
        this.idClient = idClient;
        this.person = person;
        this.registrationDateClient = registrationDateClient;
    }

    public clients() {
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

    public LocalDateTime  getRegistrationDateClient() {
        return registrationDateClient;
    }

    public void setRegistrationDateClient(LocalDateTime  registrationDateClient) {
        this.registrationDateClient = registrationDateClient;
    }

    
}
