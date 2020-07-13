package com.scan4kids.project.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private List<Photo> photos;


    public Album() {
    }

    public Album(String title, String description, List<Photo> photos, User user) {
        this.title = title;
        this.description = description;
        this.photos = photos;
        this.user = user;
    }

    //May be deleted later, testing only for now
    public Album(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Album(long id, String title, String description, List<Photo> photos, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photos = photos;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
