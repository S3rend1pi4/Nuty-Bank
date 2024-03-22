package com.nutybank.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends Person {

    // Cargo que ocupa
    private String position;
    private double salary;

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
}
