package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.dao.BankDao;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Bank getBank(UUID id) {
        Optional<Bank> c = bankDao.findById(id);
        return (c.isEmpty()) ? null : c.get();
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
