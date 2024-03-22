package com.nutybank.api.services.client;

import com.nutybank.api.entities.Account;
import com.nutybank.api.entities.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {


    @Override
    public Optional<Client> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> findeByUserName(String userName) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> findByDni(String dni) {
        return Optional.empty();
    }

    @Override
    public List<Account> findAllClientAccounts(Long userId) {
        return null;
    }

    @Override
    public Client save(Client client) {
        return null;
    }

    @Override
    public Optional<Client> update(Long id, Client client) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> delete(Long id) {
        return Optional.empty();
    }
}
