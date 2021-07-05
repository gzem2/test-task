package com.haulmont.testtask.model;

import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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

    @ManyToMany
    @JoinTable(
        name = "bankCustomers", 
        joinColumns = @JoinColumn(name = "bankId"), 
        inverseJoinColumns = @JoinColumn(name = "customerId"))
    private Set<Customer> customers;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Credit> credits;

    public Bank() {
    }

    public Bank(String bankName, Set<Customer> customers, Set<Credit> credits) {
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

    public Set<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Credit> getCredits() {
        return this.credits;
    }

    public void setCredits(Set<Credit> credits) {
        this.credits = credits;
    }
}