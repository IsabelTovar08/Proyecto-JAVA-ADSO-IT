package com.sena.crud_basic.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.branch;

public interface IBranch extends JpaRepository<branch, Integer>{
@Query("SELECT b FROM branch b WHERE " +
           "(:nameBranch IS NULL OR LOWER(b.nameBranch) LIKE LOWER(CONCAT('%', :nameBranch, '%')))")
    List<branch> filterBranchs(String nameBranch);
}
