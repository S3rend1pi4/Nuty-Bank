package com.nutybank.api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String concept;
    @NotNull
    private double quantity;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne()
    @JoinColumn(name = "origin_account_id")
    private Account originAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientTransaction;

    public Transaction() {
    }

    public Transaction(String concept, double quantity, Account originAccount, Account destinationAccount, Client clientTransaction) {
        this.concept = concept;
        this.quantity = quantity;
        this.date = new Date();
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Transaction that)) return false;
        return Double.compare(getQuantity(), that.getQuantity()) == 0 && Objects.equals(getId(), that.getId()) && Objects.equals(getConcept(), that.getConcept()) && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConcept(), getQuantity(), getDate());
    }
}
