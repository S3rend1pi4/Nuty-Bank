package com.nutybank.api.services.employee;

import com.nutybank.api.dto.EmployeeDto;
import com.nutybank.api.dto.RoleDto;
import com.nutybank.api.repositories.EmployeeRepository;
import com.nutybank.api.repositories.RoleRepository;
import com.nutybank.api.entities.Employee;
import com.nutybank.api.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Set<Role> roles = new HashSet<>();

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

        employee.setRoles(roles);

        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> update(Long id, EmployeeDto employeeDto) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()) {
            Employee employeeDb = employeeOptional.orElseThrow();
            employeeDb.setName(employeeDto.getName());
            employeeDb.setLastname(employeeDto.getLastname());
            employeeDb.setOthername(employeeDto.getOthername());
            employeeDb.setEmail(employeeDto.getEmail());
            employeeDb.setPassword(employeeDto.getPassword());
            employeeDb.setDni(employeeDto.getDni());
            employeeDb.setPosition(employeeDto.getPosition());
            employeeDb.setSalary(employeeDto.getSalary());

            Set<RoleDto> rolesDto = employeeDto.getRoles();
            Set<Role> roles = RoleDto.toRoles(rolesDto);
            employeeDb.setRoles(roles);


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

    @Override
    public boolean existsByDni(String dni) {
        return employeeRepository.existsByDni(dni);
    }
}
