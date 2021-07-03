package com.haulmont.testtask.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * Class that corresponds to Bank table in database
 */
@Entity
@Table(name = "Bank")
public class Bank {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "bankName")
    private String bankName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Customer> customers;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Credit> credits;

    public Bank() {
    }

    public Bank(String bankName, List<Customer> customers, List<Credit> credits) {
        this.bankName = bankName;
        this.customers = customers;
        this.credits = credits;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Credit> getCredits() {
        return this.credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }
}