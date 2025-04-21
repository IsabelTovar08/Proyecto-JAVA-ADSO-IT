package com.sena.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.ListMunicipality;
import com.sena.crud_basic.service.municipalityService;

@RestController
@RequestMapping("/api/municipality")
public class municipalityController {
     @Autowired
    private municipalityService municipalityService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ListMunicipality>> getAllReservationDetails(){
        List<ListMunicipality> ReservationDetails = municipalityService.getAllMunicipality();
        return ResponseEntity.ok(ReservationDetails);
    }
}
