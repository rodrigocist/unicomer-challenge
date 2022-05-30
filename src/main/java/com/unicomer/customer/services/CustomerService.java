package com.unicomer.customer.services;


import com.unicomer.customer.models.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAll();

    Optional<Customer> getById(Long id);

    ResponseEntity<String> save(Customer customer);

    ResponseEntity<String> update(Customer customer);

}
