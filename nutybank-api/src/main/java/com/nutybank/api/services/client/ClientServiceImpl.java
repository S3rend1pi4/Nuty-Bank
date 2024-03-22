package com.nutybank.api.services.client;

import com.nutybank.api.Repositories.ClientRepository;
import com.nutybank.api.Repositories.RoleRepository;
import com.nutybank.api.entities.Account;
import com.nutybank.api.entities.Client;
import com.nutybank.api.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Client> findeByUserName(String userName) {
        return clientRepository.findByName(userName);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Client> findByDni(String dni) {
        return clientRepository.findByDni(dni);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Account> findAllClientAccounts(Long userId) {
        // Buscamos al cliente por su ID
        Client client = clientRepository.findById(userId).orElseThrow();
        // Obtenemos las cuentas del cliente
        List<Account> accounts = client.getAccounts();

        return accounts;
    }

    @Transactional
    @Override
    public Client save(Client client) {
        Optional<Role> roleOptional = roleRepository.findByName("ROLE_CLIENT");
        List<Role> roles = new ArrayList<>();

        roleOptional.ifPresent(roles::add);

        if(client.isEmployee()) {
            Optional<Role> optionalRoleEmployee = roleRepository.findByName("ROLE_EMPLOYEE");
            optionalRoleEmployee.ifPresent(roles::add);
        }

        if(client.isManager()) {
            Optional<Role> optionalRoleManager = roleRepository.findByName("ROLE_MANAGER");
            optionalRoleManager.ifPresent(roles::add);
        }

        if(client.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        client.setRoles(roles);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }

    @Transactional
    @Override
    public Optional<Client> update(Long id, Client client) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isPresent()) {
            Client clientDb = clientOptional.orElseThrow();
            clientDb.setName(client.getName());
            clientDb.setLastname(client.getLastname());
            clientDb.setOthername(client.getOthername());
            clientDb.setEmail(client.getEmail());
            clientDb.setPassword(client.getPassword());
            clientDb.setRoles(client.getRoles());
            clientDb.setDni(client.getDni());
            clientDb.setAccounts(client.getAccounts());

            return Optional.of(clientRepository.save(clientDb));
        }
        return clientOptional;
    }

    @Transactional
    @Override
    public Optional<Client> delete(Long id) {
        Optional<Client> clientOptionalDb = clientRepository.findById(id);
        clientOptionalDb.ifPresent( clientDb -> {
            clientRepository.delete(clientDb);
        });
        return clientOptionalDb;
    }
}
