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
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.ListReservationDetails;
import com.sena.crud_basic.service.reservationDetailsService;

@RestController
@RequestMapping("/api/reservationDetails")
public class reservationDetailsController {
     @Autowired
    private reservationDetailsService reservationDetailService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ListReservationDetails>> getAllReservationDetails(){
        List<ListReservationDetails> ReservationDetails = reservationDetailService.getAllReservationDetail();
        return ResponseEntity.ok(ReservationDetails);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getReservationDetailsById(@PathVariable int id){
        var ReservationDetail = reservationDetailService.getReservationDetailById(id);
        return new ResponseEntity<>(ReservationDetail, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createReservationDetail(@RequestBody ListReservationDetails newReservationDetail) {
        var ReservationDetailCreate = reservationDetailService.saveReservationDetail(newReservationDetail);
        return new ResponseEntity<>(ReservationDetailCreate, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateReservationDetail(@PathVariable int id, @RequestBody ListReservationDetails ReservationDetail) {
        var updatedReservationDetail = reservationDetailService.updateReservationDetail(id, ReservationDetail);
        return new ResponseEntity<>(updatedReservationDetail, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteReservationDetail(@PathVariable int id) {
        reservationDetailService.deleteReservationDetail(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
