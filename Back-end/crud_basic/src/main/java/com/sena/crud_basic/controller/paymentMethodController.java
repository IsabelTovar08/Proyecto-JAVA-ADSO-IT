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

import com.sena.crud_basic.DTO.ListPaymentMethod;
import com.sena.crud_basic.DTO.ListPaymentMethod;
import com.sena.crud_basic.service.paymentMethodService;

@RestController
@RequestMapping("/api/paymentMethods")
public class paymentMethodController {
    @Autowired
    private paymentMethodService paymentMethodService;

    
    @GetMapping("/getAll")
    public ResponseEntity<List<ListPaymentMethod>> getAllPaymentMethods(){
        List<ListPaymentMethod> PaymentMethods = paymentMethodService.getAllPayMethod();
        return ResponseEntity.ok(PaymentMethods);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getPaymentMethodsById(@PathVariable int id){
        var PaymentMethod = paymentMethodService.getPayMethodById(id);
        return new ResponseEntity<>(PaymentMethod, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createPaymentMethod(@RequestBody ListPaymentMethod newPaymentMethod) {
        var PaymentMethodCreate = paymentMethodService.savePayMethod(newPaymentMethod);
        return new ResponseEntity<>(PaymentMethodCreate, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePaymentMethod(@PathVariable int id, @RequestBody ListPaymentMethod PaymentMethod) {
        var updatedPaymentMethod = paymentMethodService.updatePayMethod(id, PaymentMethod);
        return new ResponseEntity<>(updatedPaymentMethod, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePaymentMethod(@PathVariable int id) {
        paymentMethodService.deletePayMethod(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filter")
        public ResponseEntity<List<ListPaymentMethod>> filterPaymentMethods(
            @RequestParam(required = false) String namePaymentMethod) {
        var filteredPaymentMethods = paymentMethodService.filterPayMethods(namePaymentMethod);
        return new ResponseEntity<>(filteredPaymentMethods, HttpStatus.OK);
    }
}
