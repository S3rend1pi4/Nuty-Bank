package com.nutybank.api.services.employee;

import com.nutybank.api.dto.EmployeeDto;
import com.nutybank.api.dto.RoleDto;
import com.nutybank.api.entities.Client;
import com.nutybank.api.repositories.EmployeeRepository;
import com.nutybank.api.repositories.RoleRepository;
import com.nutybank.api.entities.Employee;
import com.nutybank.api.entities.Role;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Validator validator;

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
    public Employee save(Employee employeePost) {
        validatePassword(employeePost.getPassword());
        Employee employee = new Employee();
        employee.setName(employeePost.getName());
        employee.setLastname(employeePost.getLastname());
        employee.setOthername(employeePost.getOthername());
        employee.setEmail(employeePost.getEmail());
        employee.setDni(employeePost.getDni());
        employee.setSalary(employeePost.getSalary());
        employee.setPosition(employeePost.getPosition());
        employee.setClient(employeePost.isClient());
        employee.setManager(employeePost.isManager());
        employee.setAdmin(employeePost.isAdmin());

        // Inicializar conjunto de roles
        Set<Role> roles = new HashSet<>();

        // Asignar roles basados en los valores booleanos
        roleRepository.findByName("ROLE_EMPLOYEE").ifPresent(roles::add); // Agregar rol EMPLOYEE por defecto

        if (employee.isClient()) {
            roleRepository.findByName("ROLE_CLIENT").ifPresent(roles::add);
        }
        if (employee.isManager()) {
            roleRepository.findByName("ROLE_MANAGER").ifPresent(roles::add);
        }
        if (employee.isAdmin()) {
            roleRepository.findByName("ROLE_ADMIN").ifPresent(roles::add);
        }

        // Asignar roles al empleado
        employee.setRoles(roles);
        employee.setPassword(passwordEncoder.encode(employeePost.getPassword()));
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
            employeeDb.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
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

    private void validatePassword(String password) {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "password", password);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
