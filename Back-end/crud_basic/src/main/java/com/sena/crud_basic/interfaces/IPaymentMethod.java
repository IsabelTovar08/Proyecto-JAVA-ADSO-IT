package com.sena.crud_basic.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.payment_method;

public interface IPaymentMethod extends JpaRepository<payment_method, Integer> {
    @Query("SELECT pm FROM payment_method pm WHERE " +
           "(:namePaymentMethod IS NULL OR LOWER(pm.namePaymentMethod) LIKE LOWER(CONCAT('%', :namePaymentMethod, '%')))")
    List<payment_method> filterPaymentMethods(String namePaymentMethod);
}
