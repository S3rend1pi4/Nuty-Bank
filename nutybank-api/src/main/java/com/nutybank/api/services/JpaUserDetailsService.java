package com.nutybank.api.services;

import com.nutybank.api.entities.Client;
import com.nutybank.api.entities.Employee;
import com.nutybank.api.repositories.ClientRepository;
import com.nutybank.api.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> clientOptional = clientRepository.findByName(username);
        Optional<Employee> employeeOptional = employeeRepository.findByName(username);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.orElseThrow();

            List<GrantedAuthority> authorities = client.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());

            return new User(client.getName(),
                    client.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    authorities);
        } else if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.orElseThrow();

            List<GrantedAuthority> authorities = employee.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());

            return new User(employee.getName(),
                    employee.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    authorities);
        } else {
            throw new UsernameNotFoundException(String.format("El usuario %s no existe en el sistema!", username));
        }
    }
}
