package com.haulmont.testtask.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.haulmont.testtask.dao.CreditOfferDao;
import com.haulmont.testtask.model.CreditOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("creditOfferService")
public class CreditOfferService {
    @Autowired
    CreditOfferDao creditOfferDao;

    @Transactional
    public List<CreditOffer> getAllCreditOffers() {
        return creditOfferDao.findAll();
    }

    @Transactional
    public Optional<CreditOffer> getCreditOffer(UUID id) {
        return creditOfferDao.findById(id);
    }

    @Transactional
    public void saveCreditOffer(CreditOffer creditOffer) {
        creditOfferDao.save(creditOffer);
    }

    @Transactional
    public void deleteCreditOffer(UUID id) {
        creditOfferDao.deleteById(id);
    }
}