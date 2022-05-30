package com.unicomer.customer.services.impl;

import com.unicomer.customer.exceptions.CustomerFoundException;
import com.unicomer.customer.models.Customer;
import com.unicomer.customer.repositories.CustomerRepository;
import com.unicomer.customer.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Value("${customer.message.save.successfully}")
    private String successfullySaveMessage;

    @Value("${customer.message.save.error}")
    private String errorSaveMessage;

    @Value("${customer.message.update.successfully}")
    private String successfullyUpdateMessage;

    @Value("${customer.message.update.error}")
    private String errorUpdateMessage;



    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try{
            customers= customerRepository.findAll();
        }catch (CustomerFoundException e){
            log.error("Error getting customers", e);
        }
        return customers;
    }


    public Optional<Customer> getById(Long id) {
        Optional<Customer> customer = Optional.empty();
        try{
            customer= customerRepository.findById(id);
        }catch (CustomerFoundException e){
            log.error("Error getting customer", e);
        }
        return customer;
    }


    public ResponseEntity<String> save(Customer customer) {
        try{
            customerRepository.save(customer);
            return new ResponseEntity(successfullySaveMessage, HttpStatus.CREATED);
        }catch (CustomerFoundException e){
            log.error("Error saving customer", e);
            return new ResponseEntity(errorSaveMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> update(Customer customer) {
        try{
            customerRepository.findById(customer.getId())
                    .orElseThrow(() -> new CustomerFoundException("Not found exception"));
            customerRepository.save(customer);
            return new ResponseEntity(successfullyUpdateMessage, HttpStatus.OK);
        }catch (CustomerFoundException e){
            log.error("Error modifying customer", e);
            return new ResponseEntity(errorUpdateMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
