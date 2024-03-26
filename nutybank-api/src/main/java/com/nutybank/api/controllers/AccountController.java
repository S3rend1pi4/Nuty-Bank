package com.nutybank.api.controllers;

import com.nutybank.api.dto.AccountDto;
import com.nutybank.api.entities.Account;
import com.nutybank.api.services.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> listAll() {
        return accountService.findAll();
    }

    @PostMapping("/account/{id}")
    public ResponseEntity<?> create(@PathVariable Long id, @Valid @RequestBody AccountDto accountDto) {
        AccountDto newAccount = accountService.openAccount(id, accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
    }

}
