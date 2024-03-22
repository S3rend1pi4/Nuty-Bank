package com.nutybank.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends Person {

    // Cargo que ocupa
    private String position;
    private double salary;

    private boolean admin;
    private boolean manager;
    private boolean client;
    private boolean enabled;

    public Employee() {
    }

    public Employee(String name, String lastname, String othername, String email, String password, String dni , String position, double salary) {
        super(name, lastname, othername, email, password, dni);
        this.position = position;
        this.salary = salary;
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

    public void setSalary(double salario) {
        this.salary = salario;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isClient() {
        return client;
    }

    public void setClient(boolean client) {
        this.client = client;
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

}
