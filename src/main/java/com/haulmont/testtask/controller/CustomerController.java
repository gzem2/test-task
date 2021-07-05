package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.service.CustomerService;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public ModelAndView showAllCustomers() {
        ModelAndView modelAndView = new ModelAndView("customer/customer-list");
        modelAndView.addObject("listOfCustomers", customerService.getAllCustomers());

        return modelAndView;
    }

    @GetMapping("/customer/{id}")
    public String showCustomer(@PathVariable UUID id) {
        return "customer-details";
    }

    @GetMapping("/customer/new")
    public String showNewCustomerForm(Customer customer) {
        return "customer/customer-form";
    }

    @GetMapping("/customer/edit/{id}")
    public ModelAndView showEditCustomerForm(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView("customer/customer-form");
        Customer customerForEdit = customerService.getCustomer(id).orElseGet(() -> new Customer());
        modelAndView.addObject("customer", customerForEdit);

        return modelAndView;
    }

    @PostMapping("/customer/new")
    public ModelAndView saveCustomer(@RequestParam Map<String, String> postBody) {
        String postId = postBody.get("id");
        if (postId != null && !postId.trim().isEmpty()) {
            UUID id = UUID.fromString(postId);

            Optional<Customer> optEditCustomer = customerService.getCustomer(id);
            if (optEditCustomer.isPresent()) {
                Customer editCustomer = optEditCustomer.get();

                editCustomer.setCustomerName(postBody.get("customerName"));
                editCustomer.setPhone(postBody.get("phone"));
                editCustomer.setEmail(postBody.get("email"));
                editCustomer.setPassport(postBody.get("passport"));

                customerService.saveCustomer(editCustomer);
            }
        } else {
            Customer newCustomer = new Customer(postBody.get("customerName"), postBody.get("phone"),
                    postBody.get("email"), postBody.get("passport"), new HashSet<>());
            customerService.saveCustomer(newCustomer);
        }
        return new ModelAndView("redirect:/customers");
    }

    @GetMapping("/customer/delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return new ModelAndView("redirect:/customers");
    }
}