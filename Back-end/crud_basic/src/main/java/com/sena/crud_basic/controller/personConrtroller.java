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

import com.sena.crud_basic.DTO.ListPersonDTO;
import com.sena.crud_basic.DTO.requestDTO.requestPerson;
import com.sena.crud_basic.service.personService;

@RestController
@RequestMapping("/api/person")
public class personConrtroller {

    @Autowired
    private personService personService;

    @GetMapping("/getAll")
    public ResponseEntity<List<requestPerson>> getAllPersons(){
        List<requestPerson> Persons = personService.getAllPerson();
        return ResponseEntity.ok(Persons);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getPersonsById(@PathVariable int id){
        var Person = personService.getPersonById(id);
        return new ResponseEntity<>(Person, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createPerson(@RequestBody ListPersonDTO newPerson) {
        var PersonCreate = personService.savePerson(newPerson);
        return new ResponseEntity<>(PersonCreate, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable int id, @RequestBody ListPersonDTO Person) {
        var updatedPerson = personService.updatePerson(id, Person);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
