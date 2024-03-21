package com.nutybank.api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client extends Person {

    private String dni;
    private boolean enabled;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;

    public Client() {
        this.accounts = new ArrayList<>();
    }

    public Client(String name, String lastname, String othername, String email, String password, String dni) {
        super(name, lastname, othername, email, password);
        this.accounts = new ArrayList<>();
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
