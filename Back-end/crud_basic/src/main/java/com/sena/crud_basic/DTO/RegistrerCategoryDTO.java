package com.sena.crud_basic.DTO;

import java.util.List;


public class RegistrerCategoryDTO {

    private int idCategory;
    private String nameCategory;
    private List<ListServiceDTO> services;

    public RegistrerCategoryDTO(int idCategory, String nameCategory, List<ListServiceDTO> services) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.services = services;
    }

    public int getIdCategory() {
        return idCategory;
    }
    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
    public String getNameCategory() {
        return nameCategory;
    }
    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
    public List<ListServiceDTO> getServices() {
        return services;
    }
    public void setServices(List<ListServiceDTO> services) {
        this.services = services;
    }

    
}
