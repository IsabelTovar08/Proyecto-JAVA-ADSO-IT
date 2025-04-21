package com.sena.crud_basic.interfaces;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.reservation;

public interface IReservation extends JpaRepository<reservation, Integer> {
    // @Query("SELECT r FROM reservation r WHERE " +
    //         "(:name IS NULL OR LOWER(s.namereservation) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
    //         "(:categoryId IS NULL OR s.category.idCategory = :categoryId) AND " +
    //         "(:minPrice IS NULL OR s.basePricereservation >= :minPrice) AND " +
    //         "(:maxPrice IS NULL OR s.basePricereservation <= :maxPrice)")
    // List<reservation> filterreservations(String name, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice);

}
