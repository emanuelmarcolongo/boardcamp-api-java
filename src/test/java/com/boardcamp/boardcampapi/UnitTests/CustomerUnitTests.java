package com.boardcamp.boardcampapi.UnitTests;

import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.boardcampapi.DTOs.CustomerDTO;
import com.boardcamp.boardcampapi.exceptions.CustomerExceptions.CustomerCpfConflictException;
import com.boardcamp.boardcampapi.exceptions.CustomerExceptions.CustomerNotFoundException;
import com.boardcamp.boardcampapi.models.CustomerModel;
import com.boardcamp.boardcampapi.repositories.CustomerRepository;
import com.boardcamp.boardcampapi.services.CustomerService;

@SpringBootTest
public class CustomerUnitTests {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void givenValidCustomer_whenCreatingCustomer_thenCreatesCustomer() {

        // given
        CustomerDTO customerDTO = new CustomerDTO("name", "12345678910");
        CustomerModel newCustomer = new CustomerModel(customerDTO);

        doReturn(false).when(customerRepository).existsByCpf(any());
        doReturn(newCustomer).when(customerRepository).save(any());

        // when
        CustomerModel customer = customerService.addCustomer(customerDTO);

        // then
        assertNotNull(customer);
        verify(customerRepository, times(1)).existsByCpf(any());
        verify(customerRepository, times(1)).save(any());
        assertEquals(newCustomer, customer);
    }

    @Test
    void givenCpfInUse_whenCreatingCustomer_thenThrowsError() {

        // given
        CustomerDTO customerDTO = new CustomerDTO("name", "12345678910");

        doReturn(true).when(customerRepository).existsByCpf(any());

        // when
        CustomerCpfConflictException exception = assertThrows(CustomerCpfConflictException.class,
                () -> customerService.addCustomer(customerDTO));

        // then
        assertNotNull(exception);
        assertEquals("CPF already registered", exception.getMessage());
        verify(customerRepository, times(1)).existsByCpf(any());
        verify(customerRepository, times(0)).save(any());
    }

    @Test
    void givenValidCustomerId_whenGetCustomerById_thenReturnCustomer() {

        // given
        CustomerDTO customerDTO = new CustomerDTO("name", "12345678910");
        CustomerModel newCustomer = new CustomerModel(customerDTO);

        doReturn(Optional.of(newCustomer)).when(customerRepository).findById(any());
        doReturn(newCustomer).when(customerRepository).save(any());

        // when
        CustomerModel customer = customerService.getCustomerById(newCustomer.getId());

        // then
        assertNotNull(customer);
        assertEquals(newCustomer, customer);
        verify(customerRepository, times(1)).findById(any());
    }

    @Test
    void givenInvalidCustomerId_whenGetCustomerById_thenThrowsError() {

        // given

        doReturn(Optional.empty()).when(customerRepository).findById(any());
        // when
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
                () -> customerService.getCustomerById(any()));

        // then
        assertNotNull(exception);
        assertEquals("Customer not found with given ID", exception.getMessage());
        verify(customerRepository, times(1)).findById(any());
    }
}
