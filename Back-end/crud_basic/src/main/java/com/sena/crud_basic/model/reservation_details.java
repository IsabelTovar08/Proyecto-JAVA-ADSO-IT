package com.sena.crud_basic.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.*;

@Entity(name = "reservation_details")
public class reservation_details {
    @Id
    @Column(name = "id_reservation_details", nullable = false)
    private int idReservationDetails;

    @ManyToOne
    @JoinColumn(name = "id_reservation", nullable = false)
    private reservation reservation;

    @ManyToOne
    @JoinColumn(name = "service", nullable = false)
    private service service;

    @ManyToOne
    @JoinColumn(name = "id_employee", nullable = false)
    private employees idEmployee;

    @Column(name = "reservation_date", nullable = false)
    private Date reservationDate;

    @Column(name = "reservation_time", nullable = false)
    private Time reservationTime;

    @Column(name = "extra_charges", nullable = false, precision = 10, scale = 2)
    private BigDecimal extraCharges;

    @Column(name = "discount", nullable = false, precision = 10, scale = 2)
    private BigDecimal discount;

    @Enumerated(EnumType.STRING)
    @Column(name = "estatus_service", length = 100, nullable = false)
    private ServiceStatus estatusServicio;

    public enum ServiceStatus {
        PENDIENTE, COMPLETADO, VENCIDO
    }

    public int getIdReservationDetails() {
        return idReservationDetails;
    }

    public void setIdReservationDetails(int idReservationDetails) {
        this.idReservationDetails = idReservationDetails;
    }

    public reservation getReservation() {
        return reservation;
    }

    public void setReservation(reservation reservation) {
        this.reservation = reservation;
    }

    public service getService() {
        return service;
    }

    public void setService(service service) {
        this.service = service;
    }

    public employees getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(employees idEmployee) {
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

    public BigDecimal getExtraCharges() {
        return extraCharges;
    }

    public void setExtraCharges(BigDecimal extraCharges) {
        this.extraCharges = extraCharges;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public ServiceStatus getEstatusServicio() {
        return estatusServicio;
    }

    public void setEstatusServicio(ServiceStatus estatusServicio) {
        this.estatusServicio = estatusServicio;
    }

    

    

}
