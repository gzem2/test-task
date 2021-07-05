package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Payment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao extends JpaRepository<Payment, UUID> {
}