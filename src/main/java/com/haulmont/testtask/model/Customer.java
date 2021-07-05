package com.haulmont.testtask.model;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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

    @Column(name = "email")
    String email;

    @Column(name = "passport")
    String passport;

    @ManyToMany(mappedBy = "customers")
    private Set<Bank> banks;

    @OneToMany(mappedBy = "customer")
    private Set<CreditOffer> creditOffers;

    public Customer() {
    }

    public Customer(String customerName, String phone, String email, String passport, Set<Bank> banks) {
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.passport = passport;
        this.banks = banks;
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

    public Set<Bank> getBanks() {
        return this.banks;
    }

    public void setBanks(Set<Bank> banks) {
        this.banks = banks;
    }

    public Set<CreditOffer> getCreditOffers() {
        return this.creditOffers;
    }

    public void setCreditOffers(Set<CreditOffer> creditOffers) {
        this.creditOffers = creditOffers;
    }
}