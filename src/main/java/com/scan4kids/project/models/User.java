package com.scan4kids.project.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean isAdmin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Album> albums;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UsersEvents> userEvents;

    public User(){}

    public User(String username, String password, String email, boolean isAdmin, List<Album> albums, List<UsersEvents> userEvents) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.albums = albums;
        this.userEvents = userEvents;
    }

    public User(long id, String username, String password, String email, boolean isAdmin, List<Album> albums, List<UsersEvents> userEvents) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.albums = albums;
        this.userEvents = userEvents;
    }

    public User(User copy) {
        this.id = copy.id;
        this.username = copy.username;
        this.password = copy.password;
        this.email = copy.email;
        this.isAdmin = copy.isAdmin;
        this.albums = copy.albums;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<UsersEvents> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<UsersEvents> userEvents) {
        this.userEvents = userEvents;
    }
}
