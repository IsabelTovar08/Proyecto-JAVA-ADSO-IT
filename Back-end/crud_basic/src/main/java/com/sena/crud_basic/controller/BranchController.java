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

import com.sena.crud_basic.DTO.ListBranchDTO;
import com.sena.crud_basic.service.branchService;

@RestController
@RequestMapping("/api/branchs")

public class BranchController {
    @Autowired
    private branchService branchServicee;

    @GetMapping("/getAll")
    public ResponseEntity<List<ListBranchDTO>> getAllBranchs(){
        List<ListBranchDTO> branchs = branchServicee.getAllBranch();
        return ResponseEntity.ok(branchs);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getBranchsById(@PathVariable int id){
        var branch = branchServicee.getBranchById(id);
        return new ResponseEntity<>(branch, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createBranch(@RequestBody ListBranchDTO newBranch) {
        var BranchCreate = branchServicee.saveBranch(newBranch);
        return new ResponseEntity<>(BranchCreate, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateBranch(@PathVariable int id, @RequestBody ListBranchDTO Branch) {
        var updatedBranch = branchServicee.updateBranch(id, Branch);
        return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBranch(@PathVariable int id) {
        branchServicee.deleteBranch(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filter")
        public ResponseEntity<List<ListBranchDTO>> filterBranchs(
            @RequestParam(required = false) String nameBranch) {
        var filteredBranchs = branchServicee.filterBranchs(nameBranch);
        return new ResponseEntity<>(filteredBranchs, HttpStatus.OK);
    }
}
