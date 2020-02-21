package com.imbank.ussdbackend.controllers;

import com.imbank.ussdbackend.dto.CustomerDto;
import com.imbank.ussdbackend.entities.CustomerEntity;
import com.imbank.ussdbackend.external.requests.BalanceInquiry;
import com.imbank.ussdbackend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BalanceInquiry balanceInquiry;

    @PostMapping("/")
    public CustomerEntity createCustomer(@RequestBody CustomerDto customer){
        balanceInquiry.postTest();
        return customerService.saveCustomerDetails(customer);
    }

}
