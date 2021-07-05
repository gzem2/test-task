package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.model.CreditOffer;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface CreditOfferDao extends JpaRepository<CreditOffer, UUID> {
    public Optional<CreditOffer> findByCredit(Credit credit);
}