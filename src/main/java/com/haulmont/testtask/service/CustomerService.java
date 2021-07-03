package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.dao.CustomerDao;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customerService")
public class CustomerService {
    @Autowired
    CustomerDao customerDao;

    @Transactional
    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }
 
    @Transactional
    public Customer getCustomer(UUID id) {
        Optional<Customer> c = customerDao.findById(id);
        return (c.isEmpty()) ? null : c.get();
    }
 
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDao.save(customer);
    }
 
    @Transactional
    public void deleteCustomer(UUID id) {
        customerDao.deleteById(id);
    }
}