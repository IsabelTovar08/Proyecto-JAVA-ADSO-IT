package com.sena.crud_basic.DTO.requestDTO;

import java.time.LocalDateTime;

public class requestClients {
 private int idClient;
    private int id_person;
    private String person_name;
    private LocalDateTime registrationDateClient;
    public requestClients() {
    }
    public requestClients(int idClient, int id_person, String person_name, LocalDateTime registrationDateClient) {
        this.idClient = idClient;
        this.id_person = id_person;
        this.person_name = person_name;
        this.registrationDateClient = registrationDateClient;
    }
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public int getId_person() {
        return id_person;
    }
    public void setId_person(int id_person) {
        this.id_person = id_person;
    }
    public String getPerson_name() {
        return person_name;
    }
    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }
    public LocalDateTime getRegistrationDateClient() {
        return registrationDateClient;
    }
    public void setRegistrationDateClient(LocalDateTime registrationDateClient) {
        this.registrationDateClient = registrationDateClient;
    }
}
