package com.imbank.ussdbackend.services;

import com.imbank.ussdbackend.dto.CustomerDto;
import com.imbank.ussdbackend.entities.CustomerEntity;
import com.imbank.ussdbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity saveCustomerDetails(CustomerDto customer) {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setSecondName(customer.getSecondName());
        customerEntity.setIdNumber(customer.getIdNumber());
        customerEntity.setDateOfBirth(customer.getDateOfBirth());
        customerEntity.setPhoneNumber(customer.getPhoneNumber());


       return customerRepository.save(customerEntity);

    }
}
