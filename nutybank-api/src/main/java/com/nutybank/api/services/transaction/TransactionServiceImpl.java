package com.nutybank.api.services.transaction;

import com.nutybank.api.entities.Transaction;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {


    @Override
    public Optional<Transaction> findByClientName(String clientName) {
        return Optional.empty();
    }

    @Override
    public Optional<Transaction> findByClientDni(String dni) {
        return Optional.empty();
    }

    @Override
    public List<Transaction> findByDate(Date date) {
        return null;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return null;
    }

    @Override
    public Optional<Transaction> delete(Long id) {
        return Optional.empty();
    }
}
