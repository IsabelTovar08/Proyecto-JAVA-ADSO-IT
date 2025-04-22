package com.sena.crud_basic.DTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class ListReservationDetails {
    private int idReservationDetails;
    private int idReservation;
    private int idService;
    private String nameService;

    private int idEmployee;
    private Date reservationDate;
    private Time reservationTime;
    private BigDecimal discount;
    private LocalDateTime creationDate;
    public ListReservationDetails(int idReservationDetails, int idReservation, int idService, String nameService,
            int idEmployee, Date reservationDate, Time reservationTime, BigDecimal discount,
            LocalDateTime creationDate) {
        this.idReservationDetails = idReservationDetails;
        this.idReservation = idReservation;
        this.idService = idService;
        this.nameService = nameService;
        this.idEmployee = idEmployee;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.discount = discount;
        this.creationDate = creationDate;
    }
    public int getIdReservationDetails() {
        return idReservationDetails;
    }
    public void setIdReservationDetails(int idReservationDetails) {
        this.idReservationDetails = idReservationDetails;
    }
    public int getIdReservation() {
        return idReservation;
    }
    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }
    public int getIdService() {
        return idService;
    }
    public void setIdService(int idService) {
        this.idService = idService;
    }
    public String getNameService() {
        return nameService;
    }
    public void setNameService(String nameService) {
        this.nameService = nameService;
    }
    public int getIdEmployee() {
        return idEmployee;
    }
    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
    public Date getReservationDate() {
        return reservationDate;
    }
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
    public Time getReservationTime() {
        return reservationTime;
    }
    public void setReservationTime(Time reservationTime) {
        this.reservationTime = reservationTime;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
   


}
