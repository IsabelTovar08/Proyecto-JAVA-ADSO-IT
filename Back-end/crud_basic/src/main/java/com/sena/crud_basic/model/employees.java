package com.sena.crud_basic.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "employees")
public class employees {
    @Id
    @Column(name = "id_employee", nullable = false)
    private int idEmployee;

    @ManyToOne
    @JoinColumn(name = "id_person", nullable = false)
    private person person;

    @ManyToOne
    @JoinColumn(name = "Id_position", nullable = false)
    private position position;

    @Column(name = "salary_employee", nullable = false, precision = 10, scale = 2)
    private BigDecimal salaryEmployee;

    @OneToMany(mappedBy = "idEmployee")
    private List<reservation_details> reservationDetails = new ArrayList<>();

    public employees(int idEmployee, com.sena.crud_basic.model.person person,
            com.sena.crud_basic.model.position position, BigDecimal salaryEmployee,
            List<reservation_details> reservationDetails) {
        this.idEmployee = idEmployee;
        this.person = person;
        this.position = position;
        this.salaryEmployee = salaryEmployee;
        this.reservationDetails = reservationDetails;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public person getPerson() {
        return person;
    }

    public void setPerson(person person) {
        this.person = person;
    }

    public position getPosition() {
        return position;
    }

    public void setPosition(position position) {
        this.position = position;
    }

    public BigDecimal getSalaryEmployee() {
        return salaryEmployee;
    }

    public void setSalaryEmployee(BigDecimal salaryEmployee) {
        this.salaryEmployee = salaryEmployee;
    }

    public List<reservation_details> getReservationDetails() {
        return reservationDetails;
    }

    public void setReservationDetails(List<reservation_details> reservationDetails) {
        this.reservationDetails = reservationDetails;
    }

   
}
