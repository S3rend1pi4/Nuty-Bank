package com.nutybank.api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client extends Person {

    private boolean enabled;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;

    public Client() {
        this.accounts = new ArrayList<>();
    }

    public Client(String name, String lastname, String othername, String email, String password, String dni) {
        super(name, lastname, othername, email, password, dni);
        this.accounts = new ArrayList<>();
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
