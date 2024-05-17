package com.nutybank.api.controllers;

import com.nutybank.api.dto.TransactionDto;
import com.nutybank.api.entities.Account;
import com.nutybank.api.entities.Client;
import com.nutybank.api.entities.Transaction;
import com.nutybank.api.services.account.AccountService;
import com.nutybank.api.services.client.ClientService;
import com.nutybank.api.services.transaction.TransactionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionService;
    @Autowired
    AccountService accountService;
    @Autowired
    ClientService clientService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        Account originAccount = accountService.findById(transactionDto.getOriginAccountId());
        Account destinationAccount = accountService.findById(transactionDto.getDestinationAccountId());
        Optional<Client> clientOpt = clientService.findById(transactionDto.getClientId());

        if(originAccount != null && destinationAccount != null && clientOpt.isPresent()) {
            Transaction transaction = new Transaction(
                    transactionDto.getConcept(),
                    transactionDto.getQuantity(),
                    originAccount,
                    destinationAccount,
                    clientOpt.get());
            try {
                Transaction createdTransaction = transactionService.save(transaction);
                return ResponseEntity.ok(createdTransaction);
            } catch(IllegalAccessError e) {
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
