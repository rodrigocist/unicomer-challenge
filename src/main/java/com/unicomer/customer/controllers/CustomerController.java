package com.unicomer.customer.controllers;

import com.unicomer.customer.dto.CustomerDto;
import com.unicomer.customer.models.Customer;
import com.unicomer.customer.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerDto> getAll(){
        return customerService.getAll().stream().map(customer->modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") Long id){
        Optional<Customer> customer = customerService.getById(id);
        CustomerDto customerDto = modelMapper.map(customer.get(), CustomerDto.class);
        return customerDto;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        return new ResponseEntity(customerService.save(customer), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        return new ResponseEntity(customerService.update(customer), HttpStatus.ACCEPTED);
    }
}
