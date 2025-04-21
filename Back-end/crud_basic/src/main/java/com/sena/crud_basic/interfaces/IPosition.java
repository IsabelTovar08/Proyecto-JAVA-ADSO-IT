package com.sena.crud_basic.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.position;

public interface IPosition extends JpaRepository<position, Integer>{
 @Query("SELECT p FROM position p WHERE " +
           "(:namePosition IS NULL OR LOWER(p.namePosition) LIKE LOWER(CONCAT('%', :namePosition, '%')))")
    List<position> filterPositions(String namePosition);
}
