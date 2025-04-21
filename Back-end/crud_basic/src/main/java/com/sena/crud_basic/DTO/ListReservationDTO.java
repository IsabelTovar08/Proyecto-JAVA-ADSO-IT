package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

public class ListReservationDTO {
    private int IdReservation;
    private int idClient;
    private int idPaymentMethod;
    private LocalDateTime creationDate;
    public ListReservationDTO(int idReservation, int idClient, int idPaymentMethod, LocalDateTime creationDate) {
        IdReservation = idReservation;
        this.idClient = idClient;
        this.idPaymentMethod = idPaymentMethod;
        this.creationDate = creationDate;
    }
    public int getIdReservation() {
        return IdReservation;
    }
    public void setIdReservation(int idReservation) {
        IdReservation = idReservation;
    }
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }
    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
