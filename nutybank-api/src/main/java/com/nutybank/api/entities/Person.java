package com.nutybank.api.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="person_sequence")
    private Long id;
    private String name;
    private String lastname;
    private String othername;
    private String dni;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "person_roles",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"person_id", "role_id"})}
    )
    private Set<Role> roles;

    public Person() {
    }

    public Person(String name, String lastname, String othername, String email, String password, String dni) {
        this.name = name;
        this.lastname = lastname;
        this.othername = othername;
        this.email = email;
        this.password = password;
        this.dni = dni;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
