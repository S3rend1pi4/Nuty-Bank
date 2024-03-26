package com.nutybank.api.dto;

import com.nutybank.api.entities.Client;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDto {

    private Long id;
    private String name;
    private String lastname;
    private String othername;
    private String dni;
    private String email;
    private String password;
    private boolean employee;
    private boolean manager;
    private boolean admin;
    private Set<RoleDto> roles;
    private Set<AccountDto> accounts;

    public ClientDto() {
        this.roles = new HashSet<>();
        this.accounts = new HashSet<>();
    }

    public ClientDto(String name, String lastname, String othername, String email, String password, String dni) {
        this();
        this.name = name;
        this.lastname = lastname;
        this.othername = othername;
        this.email = email;
        this.password = password;
        this.dni = dni;
    }

    public static ClientDto toDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setLastname(client.getLastname());
        clientDto.setOthername(client.getOthername());
        clientDto.setDni(client.getDni());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getPassword());
        clientDto.setEmployee(client.isEmployee());
        clientDto.setManager(client.isManager());
        clientDto.setAdmin(client.isAdmin());

        // Mapear los roles
        Set<RoleDto> roleDtos = client.getRoles().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toSet());
        clientDto.setRoles(roleDtos);

        // Mapear las cuentas
        Set<AccountDto> accountDtos = client.getAccounts().stream()
                .map(account -> new AccountDto(account))
                .collect(Collectors.toSet());
        clientDto.setAccounts(accountDtos);

        return clientDto;
    }

    public static List<ClientDto> toDtos(List<Client> clients) {
        return clients.stream()
                .map(ClientDto::toDto)
                .collect(Collectors.toList());
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

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
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

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public Set<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountDto> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof ClientDto clientDto)) return false;
        return isEmployee() == clientDto.isEmployee() && isManager() == clientDto.isManager() && isAdmin() == clientDto.isAdmin() && Objects.equals(getId(), clientDto.getId()) && Objects.equals(getName(), clientDto.getName()) && Objects.equals(getLastname(), clientDto.getLastname()) && Objects.equals(getOthername(), clientDto.getOthername()) && Objects.equals(getDni(), clientDto.getDni()) && Objects.equals(getEmail(), clientDto.getEmail()) && Objects.equals(getPassword(), clientDto.getPassword()) && Objects.equals(getRoles(), clientDto.getRoles()) && Objects.equals(getAccounts(), clientDto.getAccounts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastname(), getOthername(), getDni(), getEmail(), getPassword(), isEmployee(), isManager(), isAdmin(), getRoles(), getAccounts());
    }
}
