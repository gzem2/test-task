package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Bank;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface BankDao extends JpaRepository<Bank, UUID> {
    
}
