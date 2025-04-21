package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

public class ListClientsDTO {
 private int idClient;
    private int id_person;
    private LocalDateTime registrationDateClient;
    public ListClientsDTO() {
    }
    public ListClientsDTO(int idClient, int id_person, LocalDateTime registrationDateClient) {
        this.idClient = idClient;
        this.id_person = id_person;
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
    public LocalDateTime getRegistrationDateClient() {
        return registrationDateClient;
    }
    public void setRegistrationDateClient(LocalDateTime registrationDateClient) {
        this.registrationDateClient = registrationDateClient;
    }
    
}
