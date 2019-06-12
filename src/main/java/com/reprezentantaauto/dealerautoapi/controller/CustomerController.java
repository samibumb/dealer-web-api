package com.reprezentantaauto.dealerautoapi.controller;


import com.reprezentantaauto.dealerautoapi.customerDTO.CreateCustomerRequest;
import com.reprezentantaauto.dealerautoapi.customerDTO.UpdateCustomerRequest;
import com.reprezentantaauto.dealerautoapi.exception.CustomerNotFoundException;
import com.reprezentantaauto.dealerautoapi.model.Customer;
import com.reprezentantaauto.dealerautoapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vehicleCustomers/")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("create")
    public ResponseEntity<Customer> create(@RequestBody @Valid CreateCustomerRequest createCustomerRequest){

       Customer response = customerService.createCustomer(createCustomerRequest);

       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Customer>> getCustomers(){

       List<Customer> customers = customerService.getCustomers();

        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @GetMapping("getCustomer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) throws CustomerNotFoundException {

       Customer response = customerService.getCustomer(id);

       return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PutMapping("update/{id}")
    public ResponseEntity updateCustomer(@PathVariable("id") Long id, @RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) throws CustomerNotFoundException {

        customerService.updateCustomer(id, updateCustomerRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") Long id){

        customerService.deleteCustomer(id);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
