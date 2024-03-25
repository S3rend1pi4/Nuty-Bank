package com.nutybank.api.services.account;

import com.nutybank.api.repositories.AccountRepository;
import com.nutybank.api.entities.Account;
import com.nutybank.api.entities.Client;
import com.nutybank.api.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientService clientService;


    @Override
    public Account openAccount(Long userId) {
        Optional<Client> clientOptional = clientService.findById(userId);

        // Crear una nueva cuenta asociada al cliente
        Account newAccount = new Account();
        if(accountRepository.existsByClientId(userId)) {
            // Cliente encontrado
            Client client = clientOptional.orElseThrow();

            newAccount.setClient(client);

            return accountRepository.save(newAccount);
        }
        return newAccount;
    }

    @Override
    public Account findAccountByUserName(String userName) {
        Optional<Account> clientOptional = accountRepository.findAccountsByClientName(userName);
        Optional<Client> client = clientService.findeByUserName(userName);
        Optional<Account> account = Optional.of(accountRepository.findByClientId(client.get().getId()).orElseThrow());
        return account.get();
    }

    @Override
    public Account findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElseThrow();
    }

    @Override
    public Account deposit(Long id, double quantity) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()) {
            double balance = account.get().getBalance();
            double newBalance = balance + quantity;

            account.get().setBalance(newBalance);

            return account.get();

        }
        return account.get();
    }

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
