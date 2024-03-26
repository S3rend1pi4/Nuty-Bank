package com.nutybank.api.dto;

import com.nutybank.api.entities.Account;
import com.nutybank.api.entities.Employee;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeDto {

    private Long id;
    private String name;
    private String lastname;
    private String othername;
    private String dni;
    private String email;
    private String password;
    private String position;
    private boolean manager;
    private boolean admin;
    private boolean client;
    private boolean enabled;
    private double salary;
    private Set<RoleDto> roles;

    public EmployeeDto() {
        this.roles = new HashSet<>();
    }

    public EmployeeDto(String name, String lastname, String othername, String email, String password, String dni, String position, double salary) {
        this();
        this.name = name;
        this.lastname = lastname;
        this.othername = othername;
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.position = position;
        this.salary = salary;
    }

    public static EmployeeDto toDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setLastname(employee.getLastname());
        employeeDto.setOthername(employee.getOthername());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPassword(employee.getPassword());
        employeeDto.setDni(employee.getDni());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setManager(employee.isManager());
        employeeDto.setAdmin(employee.isAdmin());
        employeeDto.setClient(employee.isClient());
        employeeDto.setEnabled(employee.isEnabled());

        // Mapear los roles
        Set<RoleDto> roleDtos = employee.getRoles().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toSet());

        employeeDto.setRoles(roleDtos);

        return employeeDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isClient() {
        return client;
    }

    public void setClient(boolean client) {
        this.client = client;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof EmployeeDto that)) return false;
        return isManager() == that.isManager() && isAdmin() == that.isAdmin() && isClient() == that.isClient() && isEnabled() == that.isEnabled() && Double.compare(getSalary(), that.getSalary()) == 0 && Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getLastname(), that.getLastname()) && Objects.equals(getOthername(), that.getOthername()) && Objects.equals(getDni(), that.getDni()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getPosition(), that.getPosition()) && Objects.equals(getRoles(), that.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastname(), getOthername(), getDni(), getEmail(), getPassword(), getPosition(), isManager(), isAdmin(), isClient(), isEnabled(), getSalary(), getRoles());
    }
}
