package com.haulmont.testtask.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import com.haulmont.testtask.dao.CreditDao;
import com.haulmont.testtask.dao.CreditOfferDao;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.model.CreditOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("creditService")
public class CreditService {
    @Autowired
    CreditDao creditDao;

    @Autowired
    CreditOfferDao creditOfferDao;

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
        Optional<Credit> optCredit = this.getCredit(id);
        
        if (optCredit.isPresent()) {
            Credit credit = optCredit.get();
            credit.setCreditOffers(null);
            creditDao.deleteById(id);   
        }
    }

    @Transactional
    public void removeCreditOfferFromCredit(CreditOffer creditOffer, Credit credit) {
        Set<CreditOffer> cr = credit.getCreditOffers();
        if (cr.contains(creditOffer)) {
            cr.remove(creditOffer);
            credit.setCreditOffers(cr);
            creditDao.save(credit);
        }
    }
}