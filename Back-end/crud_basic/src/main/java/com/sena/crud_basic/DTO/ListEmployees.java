package com.sena.crud_basic.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ListEmployees {

    private int idEmployee;
    private int id_person;
    private int id_position;
    private BigDecimal salaryEmployee;
    private LocalDateTime creationDate;
    public ListEmployees() {
    }
    public ListEmployees(int idEmployee, int id_person, int id_position, BigDecimal salaryEmployee,
            LocalDateTime creationDate) {
        this.idEmployee = idEmployee;
        this.id_person = id_person;
        this.id_position = id_position;
        this.salaryEmployee = salaryEmployee;
        this.creationDate = creationDate;
    }
    public int getIdEmployee() {
        return idEmployee;
    }
    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
    public int getId_person() {
        return id_person;
    }
    public void setId_person(int id_person) {
        this.id_person = id_person;
    }
    public int getId_position() {
        return id_position;
    }
    public void setId_position(int id_position) {
        this.id_position = id_position;
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
    

}
