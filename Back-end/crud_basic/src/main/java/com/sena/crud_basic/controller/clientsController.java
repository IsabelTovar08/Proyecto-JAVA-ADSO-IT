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

import com.sena.crud_basic.DTO.ListClientsDTO;
import com.sena.crud_basic.DTO.requestDTO.requestClients;
import com.sena.crud_basic.service.clientsService;

@RestController
@RequestMapping("/api/clients")
public class clientsController {
 @Autowired
 private clientsService clientsService;

     @GetMapping("/getAll")
    public ResponseEntity<List<requestClients>> getAllClientss(){
        List<requestClients> Clientss = clientsService.getAllClients();
        return ResponseEntity.ok(Clientss);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getClientssById(@PathVariable int id){
        var Clients = clientsService.getClientsById(id);
        return new ResponseEntity<>(Clients, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createClients(@RequestBody ListClientsDTO newClients) {
        var ClientsCreate = clientsService.saveClients(newClients);
        return new ResponseEntity<>(ClientsCreate, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateClients(@PathVariable int id, @RequestBody ListClientsDTO Clients) {
        var updatedClients = clientsService.updateClients(id, Clients);
        return new ResponseEntity<>(updatedClients, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteClients(@PathVariable int id) {
        clientsService.deleteClients(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
