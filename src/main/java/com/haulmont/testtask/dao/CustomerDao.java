package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Customer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface CustomerDao extends JpaRepository<Customer, UUID> {
    public Customer findByCustomerName(String customerName);

    public Customer findByPhone(String phone);

    public Customer findByEmail(String email);

    public Customer findByPassport(String passport);
}