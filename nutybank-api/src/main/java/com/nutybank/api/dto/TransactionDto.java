package com.nutybank.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nutybank.api.entities.Transaction;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionDto {

    private String concept;
    private double quantity;
    private Long originAccountId;
    private Long destinationAccountId;
    private Long clientId;

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

    public Long getOriginAccountId() {
        return originAccountId;
    }

    public void setOriginAccountId(Long originAccountId) {
        this.originAccountId = originAccountId;
    }

    public Long getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(Long destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
