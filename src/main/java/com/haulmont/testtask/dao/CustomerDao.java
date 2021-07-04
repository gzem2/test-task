package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Customer;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface CustomerDao extends JpaRepository<Customer, UUID> {
    public List<Customer> findByCustomerName(String customerName);

    public List<Customer> findByPhone(String phone);

    public List<Customer> findByEmail(String email);

    public List<Customer> findByPassport(String passport);

    public List<Customer> findByCustomerNameIgnoreCaseContaining(String customerName);

    public List<Customer> findByPhoneIgnoreCaseContaining(String phone);

    public List<Customer> findByEmailIgnoreCaseContaining(String email);

    public List<Customer> findByPassportIgnoreCaseContaining(String passport);
}