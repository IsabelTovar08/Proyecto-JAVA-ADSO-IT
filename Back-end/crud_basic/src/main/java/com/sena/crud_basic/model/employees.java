package com.sena.crud_basic.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "employees")
public class employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "idEmployee")
    private List<reservation_details> reservationDetails = new ArrayList<>();

    public employees(int idEmployee, com.sena.crud_basic.model.person person,
            com.sena.crud_basic.model.position position, BigDecimal salaryEmployee,
            LocalDateTime creationDate, List<reservation_details> reservationDetails) {
        this.idEmployee = idEmployee;
        this.person = person;
        this.position = position;
        this.salaryEmployee = salaryEmployee;
        this.creationDate = creationDate;
        this.reservationDetails = reservationDetails;
    }

    public employees() {
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
