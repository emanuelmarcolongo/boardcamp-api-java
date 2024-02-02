package com.boardcamp.boardcampapi.models;

import com.boardcamp.boardcampapi.DTOs.CustomerDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerModel {

    public CustomerModel(CustomerDTO customerDTO) {
        this.name = customerDTO.getName();
        this.cpf = customerDTO.getCpf();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 11)
    @Size(min = 11, max = 11, message = "CPF must contain 11 characters")
    private String cpf;

}
