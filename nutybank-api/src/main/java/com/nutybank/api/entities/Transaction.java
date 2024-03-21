package com.nutybank.api.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String concept;
    private double quantity;
    private Date date;

    @ManyToOne
    private Account originAccount;
    @ManyToOne
    private Account destinationAccount;
    @ManyToOne
    private Client clientTransaction;

    public Transaction() {
    }

    public Transaction(String concept, double quantity, Date date, Account originAccount, Account destinationAccount, Client clientTransaction) {
        this.concept = concept;
        this.quantity = quantity;
        this.date = date;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.clientTransaction = clientTransaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(Account originAccount) {
        this.originAccount = originAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public Client getClientTransaction() {
        return clientTransaction;
    }

    public void setClientTransaction(Client clientTransaction) {
        this.clientTransaction = clientTransaction;
    }
}
