package com.nutybank.api.services.client;

import com.nutybank.api.dto.AccountDto;
import com.nutybank.api.dto.ClientDto;
import com.nutybank.api.dto.RoleDto;
import com.nutybank.api.repositories.AccountRepository;
import com.nutybank.api.repositories.ClientRepository;
import com.nutybank.api.repositories.RoleRepository;
import com.nutybank.api.entities.Account;
import com.nutybank.api.entities.Client;
import com.nutybank.api.entities.Role;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Validator validator;

    @Transactional(readOnly = true)
    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Client> findByUserName(String userName) {
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
    public Client save(Client clientPost) {
        validatePassword(clientPost.getPassword());
        Client client = new Client();
        client.setName(clientPost.getName());
        client.setLastname(clientPost.getLastname());
        client.setOthername(clientPost.getOthername());
        client.setEmail(clientPost.getEmail());
        client.setDni(clientPost.getDni());
        client.setEmployee(clientPost.isEmployee());
        client.setManager(clientPost.isManager());
        client.setAdmin(clientPost.isAdmin());

        // Inicializar conjunto de roles
        Set<Role> roles = new HashSet<>();

        // Asignar roles basados en los valores booleanos
        roleRepository.findByName("ROLE_CLIENT").ifPresent(roles::add); // Agregar rol CLIENT por defecto

        if (client.isEmployee()) {
            roleRepository.findByName("ROLE_EMPLOYEE").ifPresent(roles::add);
        }
        if (client.isManager()) {
            roleRepository.findByName("ROLE_MANAGER").ifPresent(roles::add);
        }
        if (client.isAdmin()) {
            roleRepository.findByName("ROLE_ADMIN").ifPresent(roles::add);
        }

        // Asignar roles al cliente
        client.setRoles(roles);
        client.setPassword(passwordEncoder.encode(clientPost.getPassword()));
        return clientRepository.save(client);
    }

    @Transactional
    @Override
    public Optional<ClientDto> update(Long id, ClientDto clientDto) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isPresent()) {
            Client clientDb = clientOptional.get();
            // Actualizar campos del cliente
            clientDb.setName(clientDto.getName());
            clientDb.setLastname(clientDto.getLastname());
            clientDb.setOthername(clientDto.getOthername());
            clientDb.setEmail(clientDto.getEmail());
            clientDb.setPassword(passwordEncoder.encode(clientDto.getPassword()));
            clientDb.setDni(clientDto.getDni());
            clientDb.setEmployee(clientDto.isEmployee());
            clientDb.setManager(clientDto.isManager());
            clientDb.setAdmin(clientDto.isAdmin());
            Set<RoleDto> rolesDto = clientDto.getRoles();
            Set<Role> roles = RoleDto.toRoles(rolesDto);

            // Actualizar cuentas asociadas
            Set<Account> updatedAccounts = new HashSet<>();
            for (AccountDto accountDto : clientDto.getAccounts()) {
                Account account = modelMapper.map(accountDto, Account.class);
                account.setClient(clientDb);
                updatedAccounts.add(accountRepository.save(account));
            }

            clientDb.getAccounts().removeIf(existingAccount -> !updatedAccounts.contains(existingAccount));

            // Actualizar la lista de cuentas del cliente

            clientDb.setAccounts(updatedAccounts);
            clientDb.setRoles(roles);
            // Guardar el cliente actualizado
            return Optional.of(ClientDto.toDto(clientRepository.save(clientDb)));
        }
        return Optional.empty();
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

    @Override
    public boolean existsByDni(String dni) {
        return clientRepository.existsByDni(dni);
    }

    private void validatePassword(String password) {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "password", password);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
