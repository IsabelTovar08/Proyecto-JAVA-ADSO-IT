package com.sena.crud_basic.DTO;

import java.math.BigDecimal;

public class ListServiceDTO {
    private int idService;
    private String nameService;
    private String descriptionService;
    private BigDecimal basePriceService;
    private int idCategory;
    private String nameCategory;
    
    public ListServiceDTO(int idService, String nameService, String descriptionService, BigDecimal basePriceService,
            int idCategory, String nameCategory) {
        this.idService = idService;
        this.nameService = nameService;
        this.descriptionService = descriptionService;
        this.basePriceService = basePriceService;
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }
    public int getIdService() {
        return idService;
    }
    public void setIdService(int idService) {
        this.idService = idService;
    }
    public String getNameService() {
        return nameService;
    }
    public void setNameService(String nameService) {
        this.nameService = nameService;
    }
    public String getDescriptionService() {
        return descriptionService;
    }
    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }
    public BigDecimal getBasePriceService() {
        return basePriceService;
    }
    public void setBasePriceService(BigDecimal basePriceService) {
        this.basePriceService = basePriceService;
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


}
