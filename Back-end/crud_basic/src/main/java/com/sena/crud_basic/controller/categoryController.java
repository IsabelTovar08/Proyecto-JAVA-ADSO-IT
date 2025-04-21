package com.sena.crud_basic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.ListServiceDTO;
import com.sena.crud_basic.DTO.RegistrerCategoryDTO;
import com.sena.crud_basic.DTO.RegistrerServiceDTO;
import com.sena.crud_basic.model.category;
import com.sena.crud_basic.model.service;
import com.sena.crud_basic.service.categoryServices;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/categories")
public class categoryController {
    @Autowired
    private categoryServices categoryService;

    @GetMapping("/getAll")
    public ResponseEntity<List<RegistrerCategoryDTO>> getCategories() {
        var categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable int id) {
        var category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createCategory(@RequestBody RegistrerCategoryDTO category) {
        var newCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable int id, @RequestBody RegistrerCategoryDTO category) {
        var newCategory =categoryService.updateCategory(id, category);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
