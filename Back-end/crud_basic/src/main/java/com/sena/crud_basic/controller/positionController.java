package com.sena.crud_basic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.ListPositionDTO;
import com.sena.crud_basic.model.position;
import com.sena.crud_basic.service.positionServices;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/positions")
public class positionController {
    @Autowired
    private positionServices positionService;

    // Obtener todas las posiciones
    @GetMapping("/getAll")
    public ResponseEntity<List<ListPositionDTO>> getAllPositions() {
        List<ListPositionDTO> positions = positionService.getAllPositions();
        return ResponseEntity.ok(positions);
    }

    // Obtener posición por ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getPositionById(@PathVariable int id) {
        var position = positionService.getPositionById(id);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    // Crear una nueva posición
    @PostMapping("/")
    public ResponseEntity<Object> createPosition(@RequestBody ListPositionDTO newPosition) {
        var positionCreate = positionService.savePosition(newPosition);
        return new ResponseEntity<>(positionCreate, HttpStatus.CREATED);
    }

    // Actualizar una posición
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePosition(@PathVariable int id, @RequestBody ListPositionDTO position) {
        var updatedPosition = positionService.updatePosition(id, position);
        return new ResponseEntity<>(updatedPosition, HttpStatus.OK);
    }

    // Eliminar una posición por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePosition(@PathVariable int id) {
        positionService.deletePosition(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Filtrar posiciones por nombre
    @GetMapping("/filter")
    public ResponseEntity<List<ListPositionDTO>> filterPositions(
            @RequestParam(required = false) String namePosition) {
        var filteredPositions = positionService.filterPositions(namePosition);
        return new ResponseEntity<>(filteredPositions, HttpStatus.OK);
    }
}
