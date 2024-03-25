package com.nutybank.api.controllers;

import com.nutybank.api.entities.Account;
import com.nutybank.api.services.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/{id}")
    public ResponseEntity<?> create(@PathVariable Long id, @Valid @RequestBody Account account) {

        Account newAccount = accountService.openAccount(id, account);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);

    }
}
