package com.scan4kids.project.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private boolean isAdmin;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="users_events",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="event_id")}
    )
    private List<Event> events;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Album> albums;

    public User(){}

    public User(String username, String password, String email, boolean isAdmin, List<Event> events, List<Album> albums) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.events = events;
        this.albums = albums;
    }

    public User(long id, String username, String password, String email, boolean isAdmin, List<Event> events, List<Album> albums) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.events = events;
        this.albums = albums;
    }

    public User(User copy){
        id = copy.id;
        username = copy.username;
        password = copy.password;
        email = copy.email;
        isAdmin = copy.isAdmin;
        events = copy.events;
        albums = copy.albums;
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
