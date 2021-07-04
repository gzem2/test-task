package com.haulmont.testtask.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.haulmont.testtask.dao.CreditDao;
import com.haulmont.testtask.model.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("creditService")
public class CreditService {
    @Autowired
    CreditDao creditDao;

    @Transactional
    public List<Credit> getAllCredits() {
        return creditDao.findAll();
    }
 
    @Transactional
    public Optional<Credit> getCredit(UUID id) {
        return creditDao.findById(id);
    }

    @Transactional
    public void saveCredit(Credit credit) {
        creditDao.save(credit);
    }
 
    @Transactional
    public void deleteCredit(UUID id) {
        creditDao.deleteById(id);
    }
}