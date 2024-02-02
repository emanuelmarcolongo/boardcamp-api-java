package com.boardcamp.boardcampapi.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boardcamp.boardcampapi.DTOs.CustomerDTO;
import com.boardcamp.boardcampapi.models.CustomerModel;
import com.boardcamp.boardcampapi.services.CustomerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<CustomerModel> addCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        CustomerModel newCustomer = customerService.addCustomer(customerDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable Long id) {
        CustomerModel customer = customerService.getCustomerById(id);

        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

}
