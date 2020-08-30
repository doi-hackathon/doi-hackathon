package com.scan4kids.project.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private boolean isJudge;

    public User(){}

    public User(String username, String firstName, String lastName, String password, String email, boolean isJudge) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.isJudge = isJudge;

    }

    public User(User copy) {
        this.id = copy.id;
        this.username = copy.username;
        this.firstName = copy.firstName;
        this.lastName = copy.lastName;
        this.password = copy.password;
        this.email = copy.email;
        this.isJudge = copy.isJudge;
    }

    public User(long id, String username, String firstName, String lastName, String password, String email, boolean isJudge) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.isJudge = isJudge;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isJudge() { return isJudge; }

    public void setJudge(boolean judge) {isJudge = judge; }
}
