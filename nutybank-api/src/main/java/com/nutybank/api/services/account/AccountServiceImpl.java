package com.nutybank.api.services.account;

import com.nutybank.api.repositories.AccountRepository;
import com.nutybank.api.entities.Account;
import com.nutybank.api.entities.Client;
import com.nutybank.api.services.client.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientService clientService;


    @Transactional
    @Override
    public Account openAccount(Long userId, Account account) {
        Client client = clientService.findById(userId).orElseThrow(() -> new EntityNotFoundException("Client not found!"));

        account.setClient(client);

       return accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    @Override
    public Account findAccountByUserName(String userName) {
        Optional<Account> clientOptional = accountRepository.findAccountsByClientName(userName);
        Optional<Client> client = clientService.findeByUserName(userName);
        Optional<Account> account = Optional.of(accountRepository.findByClientId(client.get().getId()).orElseThrow());
        return account.get();
    }

    @Transactional(readOnly = true)
    @Override
    public Account findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElseThrow();
    }

    @Transactional
    @Override
    public Optional<Account> deposit(Long id, double quantity) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if(accountOptional.isPresent()) {
            Account account = accountOptional.get();
            double balance = account.getBalance();
            double newBalance = balance + quantity;

            account.setBalance(newBalance);
            accountRepository.save(account);
            return Optional.of(account);

        }
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    @Override
    public Account consultAccount() {
        return null;
    }

    /*@Override
    public Account consultAccount() {
        Optional<Client> client = get
        return null;
    }*/
}
