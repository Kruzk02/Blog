package com.UserRegistration.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "First name cannot be empty")
    @Column(name = "first_name",length = 50)
    private String first_name;

    @NotEmpty(message = "Last name cannot be empty")
    @Column(name = "last_name",length = 50)
    private String last_name;

    @NotEmpty(message = "Email cannot be empty")
    @Column(unique = true,length = 50)
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
}
