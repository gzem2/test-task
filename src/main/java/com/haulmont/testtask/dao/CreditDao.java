package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Credit;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface CreditDao extends JpaRepository<Credit, UUID> {}