package com.nutybank.api.services.account;

import com.nutybank.api.dto.AccountDto;
import com.nutybank.api.repositories.AccountRepository;
import com.nutybank.api.entities.Account;
import com.nutybank.api.entities.Client;
import com.nutybank.api.services.client.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientService clientService;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Transactional
    @Override
    public AccountDto openAccount(Long userId, AccountDto accountDto) {
        Client client = clientService.findById(userId).orElseThrow(() -> new EntityNotFoundException("Client not found!"));
        Account newAccount = new Account();
        newAccount.setAccountNumber(accountDto.getAccountNumber());
        newAccount.setBalance(accountDto.getBalance());
        newAccount.setClient(client);

       return new AccountDto(accountRepository.save(newAccount));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Account> findAccountByUserName(String userName) {
        return accountRepository.findAccountsByClientName(userName).stream().toList();
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
