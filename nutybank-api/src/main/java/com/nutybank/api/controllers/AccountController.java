package com.nutybank.api.controllers;

import com.nutybank.api.dto.AccountDto;
import com.nutybank.api.entities.Account;
import com.nutybank.api.services.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/{accountId}")
    public Optional<Account> getAccountById(@PathVariable Long accountId) {
        return Optional.ofNullable(accountService.findById(accountId));
    }

    @GetMapping("/account/{userName}")
    public ResponseEntity<?> getAccountsByUserName(@PathVariable String userName) {
        List<Account> accounts = accountService.findAccountByUserName(userName);
        if (!accounts.isEmpty()) {
            return ResponseEntity.ok(accounts);
        } else {
            throw new RuntimeException("No se encontraron cuentas para el usuario: " + userName);
        }
    }

    @PostMapping("/account/{id}")
    public ResponseEntity<?> create(@PathVariable Long id, @Valid @RequestBody AccountDto accountDto) {
        AccountDto newAccount = accountService.openAccount(id, accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
    }

    @PutMapping("/deposit/{accountId}")
    public ResponseEntity<?> deposit(@Valid @RequestBody Map<String, Double> requestQuantity, BindingResult result, @PathVariable Long accountId ) {
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        double quantity = requestQuantity.get("quantity");
        Optional<Account> account = accountService.deposit(accountId, quantity);
        if (account.isPresent()) {
            return ResponseEntity.ok(account.get());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
