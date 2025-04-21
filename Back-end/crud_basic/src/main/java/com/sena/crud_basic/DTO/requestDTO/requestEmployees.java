package com.sena.crud_basic.DTO.requestDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class requestEmployees {
    private int idEmployee;
    private int id_person;
    private String name_Person;
    private int id_position;
    private String name_position;
    private BigDecimal salaryEmployee;
    private LocalDateTime creationDate;
    public requestEmployees() {
    }
    public requestEmployees(int idEmployee, int id_person, String name_Person, int id_position, String name_position,
            BigDecimal salaryEmployee, LocalDateTime creationDate) {
        this.idEmployee = idEmployee;
        this.id_person = id_person;
        this.name_Person = name_Person;
        this.id_position = id_position;
        this.name_position = name_position;
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
    public String getName_Person() {
        return name_Person;
    }
    public void setName_Person(String name_Person) {
        this.name_Person = name_Person;
    }
    public int getId_position() {
        return id_position;
    }
    public void setId_position(int id_position) {
        this.id_position = id_position;
    }
    public String getName_position() {
        return name_position;
    }
    public void setName_position(String name_position) {
        this.name_position = name_position;
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
