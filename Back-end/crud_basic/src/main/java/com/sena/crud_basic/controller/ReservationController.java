package com.sena.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.ListReservationDTO;
import com.sena.crud_basic.service.reservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
     @Autowired
    private reservationService reservationService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ListReservationDTO>> getAllReservation(){
        List<ListReservationDTO> Reservation = reservationService.getAllReservation();
        return ResponseEntity.ok(Reservation);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getReservationById(@PathVariable int id){
        var Reservation = reservationService.getReservationById(id);
        return new ResponseEntity<>(Reservation, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createReservation(@RequestBody ListReservationDTO newReservation) {
        var ReservationCreate = reservationService.saveReservation(newReservation);
        return new ResponseEntity<>(ReservationCreate, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateReservation(@PathVariable int id, @RequestBody ListReservationDTO Reservation) {
        var updatedReservation = reservationService.updateReservation(id, Reservation);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @GetMapping("/filter")
    //     public ResponseEntity<List<ListReservationDTO>> filterReservation(
    //         @RequestParam(required = false) String nameReservation) {
    //     var filteredReservation = reservationService.filterReservation(nameReservation);
    //     return new ResponseEntity<>(filteredReservation, HttpStatus.OK);
    // }
}
