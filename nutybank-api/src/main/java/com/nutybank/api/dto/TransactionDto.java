package com.nutybank.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nutybank.api.entities.Transaction;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionDto {

    private Long id;
    private String concept;
    private double quantity;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private Long originAccountId;
    private Long destinationAccountId;

    public TransactionDto() {

    }

    public TransactionDto(Transaction transaction) {
        this.id = transaction.getId();
        this.concept = transaction.getConcept();
        this.quantity = transaction.getQuantity();
        this.date = transaction.getDate();
        this.originAccountId = transaction.getOriginAccount().getId();
        this.destinationAccountId = transaction.getDestinationAccount().getId();
    }

    public static TransactionDto toDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setConcept(transaction.getConcept());
        transactionDto.setQuantity(transaction.getQuantity());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setOriginAccountId(transaction.getOriginAccount().getId());
        transactionDto.setDestinationAccountId(transaction.getDestinationAccount().getId());
        return transactionDto;
    }

    public static List<TransactionDto> toDtos(List<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionDto::toDto)
                .collect(Collectors.toList());
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
}
