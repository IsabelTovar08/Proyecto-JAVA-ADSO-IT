package com.sena.crud_basic.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "reservation")
public class reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation", nullable = false)
    private int IdReservation;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private clients clients;

    @ManyToOne
    @JoinColumn(name = "id_payment_method", nullable = false)
    private payment_method paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_reservation", length = 100, nullable = false)
    private ReservationStatus statusReservation;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "reservation")
    private List<reservation_details> reservationDetails = new ArrayList<>();

    public reservation(int idReservation, com.sena.crud_basic.model.clients clients, payment_method paymentMethod,
            ReservationStatus statusReservation, LocalDateTime creationDate,
            List<reservation_details> reservationDetails) {
        IdReservation = idReservation;
        this.clients = clients;
        this.paymentMethod = paymentMethod;
        this.statusReservation = statusReservation;
        this.creationDate = creationDate;
        this.reservationDetails = reservationDetails;
    }

    public enum ReservationStatus {
        PENDIENTE, PAGADA, CANCELADA
    }

    public int getIdReservation() {
        return IdReservation;
    }

    public void setIdReservation(int idReservation) {
        IdReservation = idReservation;
    }

    public clients getClients() {
        return clients;
    }

    public void setClients(clients clients) {
        this.clients = clients;
    }

    public payment_method getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(payment_method paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ReservationStatus getStatusReservation() {
        return statusReservation;
    }

    public void setStatusReservation(ReservationStatus statusReservation) {
        this.statusReservation = statusReservation;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<reservation_details> getReservationDetails() {
        return reservationDetails;
    }

    public void setReservationDetails(List<reservation_details> reservationDetails) {
        this.reservationDetails = reservationDetails;
    }

    
}
