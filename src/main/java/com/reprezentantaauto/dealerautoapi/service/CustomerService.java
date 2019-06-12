package com.reprezentantaauto.dealerautoapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reprezentantaauto.dealerautoapi.customerDTO.CreateCustomerRequest;
import com.reprezentantaauto.dealerautoapi.customerDTO.GetCustomerRequest;
import com.reprezentantaauto.dealerautoapi.customerDTO.UpdateCustomerRequest;
import com.reprezentantaauto.dealerautoapi.exception.CustomerNotFoundException;
import com.reprezentantaauto.dealerautoapi.exception.ResourceNotFoundException;
import com.reprezentantaauto.dealerautoapi.model.Customer;
import com.reprezentantaauto.dealerautoapi.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private static final Logger LOGGER= LoggerFactory.getLogger(CustomerService.class);

    private  final CustomerRepository customerRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    //creating CRUD operations

    public Customer createCustomer(CreateCustomerRequest createCustomerRequest){
        LOGGER.info("Creating Customer {}",createCustomerRequest);

        Customer customer=objectMapper.convertValue(createCustomerRequest,Customer.class);

        return customerRepository.save(customer);


       /**
        * another way for creating Customer

        * Customer customer = new Customer();
        *customer.setFirstName(createCustomerRequest.getFirstName());
        *customer.setLastName(createCustomerRequest.getLastName());
        *customer.setAge(createCustomerRequest.getAge());
        *customer.seteMail(createCustomerRequest.geteMail());

        return customerRepository.save(customer);
        */
    }

    public List<Customer> getCustomers(){
        LOGGER.info("Getting list of customers");
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id) throws CustomerNotFoundException {

        LOGGER.info("Getting customer by id {}",id);
        return customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer "+id+" does not exist."));
    }

    public Customer updateCustomer(Long id ,UpdateCustomerRequest updateCustomerRequest) throws CustomerNotFoundException {

        LOGGER.info("Update customer {} with {}",id,updateCustomerRequest);
        Customer customer = getCustomer(id);

        BeanUtils.copyProperties(updateCustomerRequest,customer);

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id){
        LOGGER.info("Deleting customer {}",id);
        customerRepository.deleteById(id);
        LOGGER.info("Customer {} deleted",id);
    }

}
