package com.scan4kids.project.models;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<User> users;

    @Column(nullable = false)
    private String role;

    public Role() {
    }

    public Role(long id, List<User> users, String role) {
        this.id = id;
        this.users = users;
        this.role = role;
    }

    public Role(List<User> users, String role){
        this.users = users;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
