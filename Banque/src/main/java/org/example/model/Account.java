package org.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;

    private String IBAN;

    private float solde;
    @ManyToOne
    @JoinColumn(name="agency_id")
    private Agency agency;

    @ManyToMany(mappedBy = "accounts")
    private List<Customer> customers = new ArrayList<>();


    public Account() {
    }

    public Account(String libelle, String IBAN, float solde) {
        this.libelle = libelle;
        this.IBAN = IBAN;
        this.solde = solde;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
        customer.getAccounts().add(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", solde=" + solde +
                ", agency=" + agency +
                ", customers=" + customers +
                '}';
    }
}

