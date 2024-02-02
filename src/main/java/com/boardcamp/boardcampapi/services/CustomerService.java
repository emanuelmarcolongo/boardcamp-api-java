package com.boardcamp.boardcampapi.services;

import org.springframework.stereotype.Service;

import com.boardcamp.boardcampapi.DTOs.CustomerDTO;
import com.boardcamp.boardcampapi.exceptions.CustomerExceptions.CustomerCpfConflictException;
import com.boardcamp.boardcampapi.exceptions.CustomerExceptions.CustomerNotFoundException;
import com.boardcamp.boardcampapi.models.CustomerModel;
import com.boardcamp.boardcampapi.repositories.CustomerRepository;

@Service
public class CustomerService {

    final CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerModel addCustomer(CustomerDTO customerDTO) {

        boolean CpfInUse = customerRepository.existsByCpf(customerDTO.getCpf());
        if (CpfInUse) {
            throw new CustomerCpfConflictException("CPF already registered");
        }

        CustomerModel newCustomer = new CustomerModel(customerDTO);
        return customerRepository.save(newCustomer);

    }

    public CustomerModel getCustomerById(Long id) {
        CustomerModel customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with given ID"));

        return customer;
    }

}
