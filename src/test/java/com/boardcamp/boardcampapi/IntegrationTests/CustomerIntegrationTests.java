package com.boardcamp.boardcampapi.IntegrationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.boardcamp.boardcampapi.DTOs.CustomerDTO;
import com.boardcamp.boardcampapi.models.CustomerModel;
import com.boardcamp.boardcampapi.repositories.CustomerRepository;
import com.boardcamp.boardcampapi.repositories.RentalRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CustomerIntegrationTests {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    @AfterEach
    void cleanUpDatabase() {
        rentalRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    void givenValidCustomer_whenCreatingCustomer_thenCreatesCustomer() {
        // given
        CustomerDTO customerDTO = new CustomerDTO("name", "12345678911");
        HttpEntity<CustomerDTO> body = new HttpEntity<>(customerDTO);

        // when
        ResponseEntity<CustomerModel> response = restTemplate.exchange("/customers", HttpMethod.POST, body,
                CustomerModel.class);

        // then

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, customerRepository.count());
    }

    @Test
    void givenInUseCpf_whenCreatingCustomer_thenThrowsError() {
        // given
        CustomerDTO customerDTO = new CustomerDTO("name", "12345678911");
        CustomerModel newCustomer = new CustomerModel(customerDTO);
        customerRepository.save(newCustomer);
        HttpEntity<CustomerDTO> body = new HttpEntity<>(customerDTO);

        // when
        ResponseEntity<String> response = restTemplate.exchange("/customers", HttpMethod.POST, body,
                String.class);

        System.out.println(response);
        // then

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(1, customerRepository.count());
    }

    @Test
    void givenValidId_whenGettingCustomer_thenReturnCustomer() {
        // given
        CustomerDTO customerDTO = new CustomerDTO("name", "12345678911");
        CustomerModel newCustomer = new CustomerModel(customerDTO);
        CustomerModel savedCustomer = customerRepository.save(newCustomer);

        Long customerId = savedCustomer.getId();

        // when
        ResponseEntity<CustomerModel> response = restTemplate.exchange("/customers/" + customerId, HttpMethod.GET, null,
                CustomerModel.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedCustomer, response.getBody());
    }

    @Test
    void givenInvalidId_whenGettingCustomer_thenThrowsError() {
        // given

        // when
        ResponseEntity<String> response = restTemplate.exchange("/customers/123324", HttpMethod.GET, null,
                String.class);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Customer not found with given ID", response.getBody());
    }
}
