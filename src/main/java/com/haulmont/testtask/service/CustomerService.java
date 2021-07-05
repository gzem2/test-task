package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.dao.CustomerDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customerService")
public class CustomerService {
    @Autowired
    CustomerDao customerDao;

    @Autowired
    BankService bankService;

    @Transactional
    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }

    @Transactional
    public Optional<Customer> getCustomer(UUID id) {
        return customerDao.findById(id);
    }

    @Transactional
    public void saveCustomer(Customer customer) {
        customerDao.save(customer);
    }

    @Transactional
    public void deleteCustomer(UUID id) {
        Set<Bank> customerBanks = bankService.getBanksForCustomerId(id);
        for (Bank b : customerBanks) {
            bankService.removeCustomerFromBank(b, customerDao.getById(id));
        }
        customerDao.deleteById(id);
    }

    @Transactional
    public List<Customer> searchCustomer(Customer searchCustomer) {
        List<Customer> found = new ArrayList<>();

        if (!searchCustomer.getCustomerName().trim().isEmpty()) {
            found = customerDao.findByCustomerNameIgnoreCaseContaining(searchCustomer.getCustomerName());
        } else if (!searchCustomer.getPhone().trim().isEmpty()) {
            found = customerDao.findByPhoneIgnoreCaseContaining(searchCustomer.getPhone());
        } else if (!searchCustomer.getEmail().trim().isEmpty()) {
            found = customerDao.findByEmailIgnoreCaseContaining(searchCustomer.getEmail());
        } else if (!searchCustomer.getPassport().trim().isEmpty()) {
            found = customerDao.findByPassportIgnoreCaseContaining(searchCustomer.getPassport());
        }

        return found;
    }

    @Transactional
    public void removeCreditOfferFromCustomer(CreditOffer creditOffer, Customer customer) {
        Set<CreditOffer> cr = customer.getCreditOffers();
        if (cr.contains(creditOffer)) {
            cr.remove(creditOffer);
            customer.setCreditOffers(cr);
            customerDao.save(customer);
        }
    }
}