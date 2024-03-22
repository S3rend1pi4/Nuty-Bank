package com.nutybank.api.services.employee;

import com.nutybank.api.Repositories.EmployeeRepository;
import com.nutybank.api.Repositories.RoleRepository;
import com.nutybank.api.entities.Employee;
import com.nutybank.api.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> findeByEmployeeName(String employeeName) {
        return employeeRepository.findByName(employeeName);
    }

    @Override
    public Optional<Employee> findByDni(String dni) {
        return employeeRepository.findByDni(dni);
    }

    @Override
    public Employee save(Employee employee) {
        Optional<Role> roleOptional = roleRepository.findByName("ROLE_EMPLOYEE");
        List<Role> roles = new ArrayList<>();

        roleOptional.ifPresent(roles::add);

        if(employee.isClient()) {
            Optional<Role> optionalRoleEmployee = roleRepository.findByName("ROLE_CLIENT");
            optionalRoleEmployee.ifPresent(roles::add);
        }

        if(employee.isManager()) {
            Optional<Role> optionalRoleManager = roleRepository.findByName("ROLE_MANAGER");
            optionalRoleManager.ifPresent(roles::add);
        }

        if(employee.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> update(Long id, Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()) {
            Employee employeeDb = employeeOptional.orElseThrow();
            employeeDb.setName(employee.getName());
            employeeDb.setLastname(employee.getLastname());
            employeeDb.setOthername(employee.getOthername());
            employeeDb.setEmail(employee.getEmail());
            employeeDb.setPassword(employee.getPassword());
            employeeDb.setRoles(employee.getRoles());
            employeeDb.setDni(employee.getDni());
            employeeDb.setPosition(employee.getPosition());
            employeeDb.setSalary(employee.getSalary());

            return Optional.of(employeeRepository.save(employeeDb));
        }
        return employeeOptional;
    }

    @Override
    public Optional<Employee> delete(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        employeeOptional.ifPresent(employeeDb -> {
            employeeRepository.delete(employeeDb);
        });
        return employeeOptional;
    }
}