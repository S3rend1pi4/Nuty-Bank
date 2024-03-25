package com.nutybank.api.services.transaction;

import com.nutybank.api.Repositories.TransactionRepository;
import com.nutybank.api.entities.Transaction;
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

    @Override
    public Optional<Transaction> findByClientName(String clientName) {

        return transactionRepository.findByClientTransactionName(clientName);
    }

    @Override
    public Optional<Transaction> findByClientDni(String dni) {
        return transactionRepository.findByClientTransactionDni(dni);
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
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> delete(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        transactionOptional.ifPresent(transaction -> transactionRepository.delete(transaction));
        return transactionOptional;
    }
}
