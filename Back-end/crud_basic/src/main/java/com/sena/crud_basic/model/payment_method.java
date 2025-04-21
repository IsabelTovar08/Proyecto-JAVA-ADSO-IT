package com.sena.crud_basic.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "payment_method")
public class payment_method {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment_method", nullable = false)
    private int idPaymentMethod;

    @Column(name = "name_payment_method", length = 200, nullable = false)
    private String namePaymentMethod;

    @OneToMany(mappedBy = "paymentMethod")
    private List<reservation> reservations = new ArrayList<>();

    public payment_method() {
    }

    public payment_method(int idPaymentMethod, String namePaymentMethod,
            List<reservation> reservations) {
        this.idPaymentMethod = idPaymentMethod;
        this.namePaymentMethod = namePaymentMethod;
        this.reservations = reservations;
    }

    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public String getNamePaymentMethod() {
        return namePaymentMethod;
    }

    public void setNamePaymentMethod(String namePaymentMethod) {
        this.namePaymentMethod = namePaymentMethod;
    }

    public List<reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<reservation> reservations) {
        this.reservations = reservations;
    }

    
}
