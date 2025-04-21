package com.sena.crud_basic.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.reservation_details;

public interface IReservationDetails extends JpaRepository<reservation_details, Integer>  {

}
