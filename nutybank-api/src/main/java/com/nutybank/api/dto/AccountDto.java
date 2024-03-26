package com.nutybank.api.dto;

import com.nutybank.api.entities.Account;

import java.util.Objects;

public class AccountDto {

    private Long id;
    private String accountNumber;
    private double balance;
    private ClientDto client;
    private Long clientId;

    public AccountDto() {
    }

    public AccountDto(Account account) {
        this.id = account.getId();
        this.accountNumber = account.getAccountNumber();
        this.balance = account.getBalance();
        this.clientId = account.getClient().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof AccountDto that)) return false;
        return Double.compare(getBalance(), that.getBalance()) == 0 && Objects.equals(getId(), that.getId()) && Objects.equals(getAccountNumber(), that.getAccountNumber()) && Objects.equals(getClient(), that.getClient()) && Objects.equals(getClientId(), that.getClientId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccountNumber(), getBalance(), getClient(), getClientId());
    }
}
