package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.dao.BankDao;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bankService")
public class BankService {
    @Autowired
    BankDao bankDao;

    @Transactional
    public List<Bank> getAllBanks() {
        return bankDao.findAll();
    }
 
    @Transactional
    public Optional<Bank> getBank(UUID id) {
        return bankDao.findById(id);
    }

    @Transactional
    public void addCustomerToBank(Bank bank, Customer customer) {
        Set<Customer> bc = bank.getCustomers();
        Set<UUID> ids = bc.stream().map(b -> b.getId()).collect(Collectors.toSet());
        if(!ids.contains(customer.getId())) {
            bc.add(customer);
            bank.setCustomers(bc);
            bankDao.save(bank);
        }
    }

    @Transactional
    public Set<Bank> getBanksForCustomerId(UUID id) {
        Set<Bank> found = new HashSet<>();
        List<Bank> banks = this.getAllBanks();
        for(Bank b : banks) {
            if(b.getCustomers().stream().map(c -> c.getId()).collect(Collectors.toSet()).contains(id)) {
                found.add(b);
            }
        }

        return found;
    }

    @Transactional
    public void removeCustomerFromBank(Bank bank, Customer customer) {
        Set<Customer> bc = bank.getCustomers();
        if(bc.contains(customer)) {
            bc.remove(customer);
            bank.setCustomers(bc);
            bankDao.save(bank);    
        }
    }
 
    @Transactional
    public void saveBank(Bank bank) {
        bankDao.save(bank);
    }
 
    @Transactional
    public void deleteBank(UUID id) {
        bankDao.deleteById(id);
    }
}
