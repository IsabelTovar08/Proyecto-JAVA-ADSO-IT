package com.sena.crud_basic.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.clients;

@Repository
public interface IClients extends JpaRepository<clients,Integer> {

}
