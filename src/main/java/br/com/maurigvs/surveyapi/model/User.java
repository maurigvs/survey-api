package br.com.maurigvs.surveyapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String login;

    private String password;

    private LocalDateTime createdAt;

    public User(Long id, String name, String email, String login, String password, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.createdAt = createdAt;
    }

    protected User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
