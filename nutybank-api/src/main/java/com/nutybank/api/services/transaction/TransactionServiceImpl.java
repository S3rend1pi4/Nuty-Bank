package com.nutybank.api.services.transaction;

import com.nutybank.api.entities.Account;
import com.nutybank.api.entities.Client;
import com.nutybank.api.repositories.TransactionRepository;
import com.nutybank.api.entities.Transaction;
import com.nutybank.api.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> findByClientName(String clientName) {
        List<Transaction> allTransactions = transactionRepository.findAll();
        List<Transaction> transactionByClientName = new ArrayList<>();

        for (Transaction transaction : allTransactions) {
            if (transaction.getClientTransaction().getName().equals(clientName)) {
                transactionByClientName.add(transaction);
            }
        }

        return transactionByClientName;
    }

    @Override
    public List<Transaction> findByClientDni(String dni) {
        List<Transaction> allTransactions = transactionRepository.findAll();
        List<Transaction> transactionByClientDni = new ArrayList<>();

        for (Transaction transaction : allTransactions) {
            if (transaction.getClientTransaction().getDni().equals(dni)) {
                transactionByClientDni.add(transaction);
            }
        }

        return transactionByClientDni;
    }

    @Override
    public List<Transaction> findByDate(Date date) {
        List<Transaction> allTransactions = transactionRepository.findAll();

        List<Transaction> transactionByDate = new ArrayList<>();

        for(Transaction transaction : allTransactions) {
            if(transaction.getDate().equals(date)) {
                transactionByDate.add(transaction);
            }
        }
        return transactionByDate;
    }

    @Override
    public Transaction save(Transaction transaction) {
        Account originAccount = transaction.getOriginAccount();
        Account destinationAccount = transaction.getDestinationAccount();
        Client clientOptTransaction = originAccount.getClient();
        transaction.setClientTransaction(clientOptTransaction);
        double quantity = transaction.getQuantity();

        if (originAccount.getBalance() < quantity) {
            throw new IllegalArgumentException("Saldo de la cuenta de origen insuficiente");
        }
        originAccount.setBalance(originAccount.getBalance() - quantity);
        destinationAccount.setBalance(destinationAccount.getBalance() + quantity);
        if(transaction.getDate() == null) {
            transaction.setDate(new Date());
        }

        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> delete(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        Account originAccount = transactionOptional.get().getOriginAccount();
        Account destinationAccount = transactionOptional.get().getOriginAccount();
        double quantity = transactionOptional.get().getQuantity();
        if(originAccount != null && destinationAccount != null) {
            accountService.deposit(originAccount.getId(), originAccount.getBalance() + quantity);
            accountService.deposit(destinationAccount.getId(), destinationAccount.getBalance() - quantity);
            //destinationAccount.setBalance(destinationAccount.getBalance() - quantity);
        }

        transactionOptional.ifPresent(transaction -> transactionRepository.delete(transaction));
        return transactionOptional;
    }
}
