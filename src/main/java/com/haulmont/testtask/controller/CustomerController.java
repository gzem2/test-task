package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.service.CustomerService;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public String showAllCustomers() {
        return "customers-list";
    }

    @GetMapping("/customers/{id}")
    public String showCustomer(@PathVariable UUID id) {
        return "customer-details";
    }

    @PostMapping("/customers")
    public String newCustomer(@RequestBody Customer newCustomer) {
        customerService.addCustomer(newCustomer);
        return "customers-list";
    }
}