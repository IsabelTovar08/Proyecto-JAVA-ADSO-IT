package com.sena.crud_basic.interfaces;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.service;

@Repository
public interface IServiceRepository extends JpaRepository<service, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar servicios por nombre o categoría
    // List<service> findByName(String name);

    /* JPAReposototry incluye
     * SELECT
     * INSERT
     * UPDATE
     * DELETE
     * Por defecto
     */

    // @Query("SELECT s FROM service s JOIN FETCH s.category")
    // List<service> findAllWithCategory();

@Query("SELECT s FROM service s WHERE " +
       "(:name IS NULL OR LOWER(s.nameService) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
       "(:categoryId IS NULL OR s.category.idCategory = :categoryId) AND " +
       "(:minPrice IS NULL OR s.basePriceService >= :minPrice) AND " +
       "(:maxPrice IS NULL OR s.basePriceService <= :maxPrice)")
List<service> filterServices(String name, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice);


}
