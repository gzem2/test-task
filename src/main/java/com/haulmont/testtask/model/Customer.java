package com.haulmont.testtask.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Class that corresponds to Customer table in database
 */
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "customerName")
    String customerName;

    @Column(name = "phone")
    String phone;

    @Column(name="email")
    String email;

    @Column(name = "passport")
    String passport;

    public Customer() {
    }

    public Customer(String customerName, String phone, String email, String passport) {
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.passport = passport;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}