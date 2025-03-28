package com.sena.crud_basic.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity(name = "service")

public class service {

    // @Id Indica que es la Primaty Key 
    @Id
    // @Colum indica que es una columna
    /*
     * name = Es el nombre de la columna en la base de datos
     * length = 10 es la longitud de la columna
     * nullable = fSi acepta nulo o no
     */
    @Column(name = "id_service", nullable = false)
    private int idService;

    @Column(name = "name_service", length = 100, nullable = false)
    private String nameService;

    @Column(name = "description_service", length = 200, nullable = true)
    private String descriptionService;

    @Column(name = "base_price_sevice", precision = 10, scale = 2, nullable = false)
    private BigDecimal basePriceService;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private category category;

    public service(int idService, String nameService, String descriptionService, BigDecimal basePriceService,
            com.sena.crud_basic.model.category category) {
        this.idService = idService;
        this.nameService = nameService;
        this.descriptionService = descriptionService;
        this.basePriceService = basePriceService;
        this.category = category;
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

    public category getCategory() {
        return category;
    }

    public void setCategory(category category) {
        this.category = category;
    }

    

    
}
