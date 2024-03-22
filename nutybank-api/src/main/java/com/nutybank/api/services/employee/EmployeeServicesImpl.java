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
    public Optional<Employee> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findeByEmployeeName(String employeeName) {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findByDni(String dni) {
        return Optional.empty();
    }

    @Override
    public Employee save(Employee employee) {
        return null;
    }

    @Override
    public Optional<Employee> update(Long id, Employee employee) {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> delete(Long id) {
        return Optional.empty();
    }
}
