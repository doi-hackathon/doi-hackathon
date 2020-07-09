package com.scan4kids.project.models;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 200)
    private String location;

//    @Column(nullable = false, date)
//    private Date

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(nullable = true)
    private String link;

    @Column(int)
    private int volunteerGoal;

    public Event(){}

    public Event(String title, String location, String description, String link, int volunteerGoal) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.link = link;
        this.volunteerGoal = volunteerGoal;
    }

    public Event(long id, String title, String location, String description, String link, int volunteerGoal) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.link = link;
        this.volunteerGoal = volunteerGoal;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public int getVolunteerGoal() {
        return volunteerGoal;
    }

    public void setVolunteerGoal(int volunteerGoal) {
        this.volunteerGoal = volunteerGoal;
    }
}
