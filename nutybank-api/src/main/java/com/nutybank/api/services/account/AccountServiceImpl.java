package com.nutybank.api.services.account;

import com.nutybank.api.entities.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public Account openAccount(Long userId) {
        return null;
    }

    @Override
    public Account findAccountByUserName(String userName) {
        return null;
    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public Account deposit(Long id, double quantity) {
        return null;
    }

    @Override
    public Account consultAccount() {
        return null;
    }
}
