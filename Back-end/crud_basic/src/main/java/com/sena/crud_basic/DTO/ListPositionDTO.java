package com.sena.crud_basic.DTO;

public class ListPositionDTO {
    private int idPosition;
    private String namePosition;

    public ListPositionDTO(int idPosition, String namePosition) {
        this.idPosition = idPosition;
        this.namePosition = namePosition;
    }
    
    public int getIdPosition() {
        return idPosition;
    }
    public void setIdPosition(int idPosition) {
        this.idPosition = idPosition;
    }
    public String getNamePosition() {
        return namePosition;
    }
    public void setNamePosition(String namePosition) {
        this.namePosition = namePosition;
    }
}
