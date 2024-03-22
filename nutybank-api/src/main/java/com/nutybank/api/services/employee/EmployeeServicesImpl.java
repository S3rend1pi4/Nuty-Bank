package com.nutybank.api.services.employee;

import com.nutybank.api.entities.Account;
import com.nutybank.api.entities.Client;
import com.nutybank.api.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServicesImpl implements EmployeeService {


    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return null;
    }

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
