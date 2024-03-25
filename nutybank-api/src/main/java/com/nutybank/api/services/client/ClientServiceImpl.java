package com.nutybank.api.services.client;

import com.nutybank.api.repositories.AccountRepository;
import com.nutybank.api.repositories.ClientRepository;
import com.nutybank.api.repositories.RoleRepository;
import com.nutybank.api.entities.Account;
import com.nutybank.api.entities.Client;
import com.nutybank.api.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRepository accountRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

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
    public Set<Account> findAllClientAccounts(Long userId) {
        // Buscamos al cliente por su ID
        Client client = clientRepository.findById(userId).orElseThrow();
        // Obtenemos las cuentas del cliente
        Set<Account> accounts = client.getAccounts();

        return accounts;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
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
        client.setPassword(client.getPassword());
        return clientRepository.save(client);
    }

    @Transactional
    @Override
    public Optional<Client> update(Long id, Client client) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isPresent()) {
            Client clientDb = clientOptional.get();
            // Actualizar campos del cliente
            clientDb.setName(client.getName());
            clientDb.setLastname(client.getLastname());
            clientDb.setOthername(client.getOthername());
            clientDb.setEmail(client.getEmail());
            clientDb.setPassword(client.getPassword());
            clientDb.setRoles(client.getRoles());
            clientDb.setDni(client.getDni());

            // Actualizar cuentas asociadas
            Set<Account> updatedAccounts = new HashSet<>();
            for (Account account : client.getAccounts()) {
                account.setClient(clientDb);
                updatedAccounts.add(accountRepository.save(account));
            }

            clientDb.getAccounts().removeIf(existingAccount -> !updatedAccounts.contains(existingAccount));

            // Actualizar la lista de cuentas del cliente
            clientDb.setAccounts(updatedAccounts);

            // Guardar el cliente actualizado
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
