package com.scan4kids.project.models;

import javax.persistence.*;

@Entity
@Table(name="photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String link;

    @ManyToOne
    @JoinColumn (name = "album_id")
    private Album album;


    public Photo() {
    }

    //may be deleted later
    public Photo(String description, String link) {
        this.description = description;
        this.link = link;
    }

    public Photo(String description, String link, Album album){
        this.description = description;
        this.link = link;
        this.album = album;
    }

    public Photo(long id, String description, String link, Album album){
        this.id = id;
        this.description = description;
        this.link = link;
        this.album = album;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}