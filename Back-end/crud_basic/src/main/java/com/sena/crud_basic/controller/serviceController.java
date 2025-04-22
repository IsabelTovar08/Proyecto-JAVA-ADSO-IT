package com.sena.crud_basic.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.ListServiceDTO;
import com.sena.crud_basic.DTO.RegistrerServiceDTO;
import com.sena.crud_basic.model.service;
import com.sena.crud_basic.service.serviceServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/services")

public class serviceController {
    @Autowired
    private serviceServices serviceService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ListServiceDTO>> getAllServices() {
        List<ListServiceDTO> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getServiceById(@PathVariable int id) {
        var service = serviceService.getServiceById(id);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    
    @PostMapping("/")
    public ResponseEntity<Object> createService(@RequestBody RegistrerServiceDTO service) {
        var newService = serviceService.saveService(service);
        return new ResponseEntity<>(newService, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> createService(@PathVariable int id, @RequestBody RegistrerServiceDTO service) {
        var newService = serviceService.updateService(id,service);
        return new ResponseEntity<>(newService,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable int id) {
        serviceService.deleteService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
@GetMapping("/filter")
public ResponseEntity<List<ListServiceDTO>> filterServices(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Integer categoryId,
        @RequestParam(required = false) BigDecimal minPrice,
        @RequestParam(required = false) BigDecimal maxPrice) {

    var filteredServices = serviceService.filterServices(name, categoryId, minPrice, maxPrice);
    return new ResponseEntity<>(filteredServices, HttpStatus.OK);
}


}
