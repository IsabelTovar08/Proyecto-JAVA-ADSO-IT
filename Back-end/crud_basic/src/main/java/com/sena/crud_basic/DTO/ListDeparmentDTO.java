package com.sena.crud_basic.DTO;

public class ListDeparmentDTO {
    private int idDeparment;
    private String nameDeparment;
    public ListDeparmentDTO(int idDeparment, String nameDeparment) {
        this.idDeparment = idDeparment;
        this.nameDeparment = nameDeparment;
    }
    public int getId() {
        return idDeparment;
    }
    public void setId(int idDeparment) {
        this.idDeparment = idDeparment;
    }
    public String getNameDeparment() {
        return nameDeparment;
    }
    public void setNameDeparment(String nameDeparment) {
        this.nameDeparment = nameDeparment;
    }
}
