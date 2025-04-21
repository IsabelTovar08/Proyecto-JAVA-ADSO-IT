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

import com.sena.crud_basic.DTO.ListEmployees;
import com.sena.crud_basic.DTO.requestDTO.requestEmployees;
import com.sena.crud_basic.service.employeesService;

@RestController
@RequestMapping("/api/employees")
public class employeesController {
 @Autowired
 private employeesService employeesService;

      @GetMapping("/getAll")
    public ResponseEntity<List<requestEmployees>> getAllEmployeess(){
        List<requestEmployees> Employeess = employeesService.getAllEmployees();
        return ResponseEntity.ok(Employeess);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getEmployeessById(@PathVariable int id){
        var Employees = employeesService.getEmployeesById(id);
        return new ResponseEntity<>(Employees, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createEmployees(@RequestBody ListEmployees newEmployees) {
        var EmployeesCreate = employeesService.saveEmployees(newEmployees);
        return new ResponseEntity<>(EmployeesCreate, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEmployees(@PathVariable int id, @RequestBody ListEmployees Employees) {
        var updatedEmployees = employeesService.updateEmployees(id, Employees);
        return new ResponseEntity<>(updatedEmployees, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteEmployees(@PathVariable int id) {
        employeesService.deleteEmployees(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
