package com.nutybank.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends Person {

    // Cargo que ocupa
    private String position;
    private double salario;

    public Employee() {
    }

    public Employee(String name, String lastname, String othername, String email, String password, String position, double salario) {
        super(name, lastname, othername, email, password);
        this.position = position;
        this.salario = salario;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
