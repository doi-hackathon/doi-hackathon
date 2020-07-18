package com.scan4kids.project.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;

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

    @Column
    private String dateAndTime;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(nullable = true)
    private String link;

    @Column(nullable = true)
    private int volunteerGoal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<UsersEvents> userEvents;

    public Event(){}

    public Event(String title, String location, String dateAndTime, String description, String link, int volunteerGoal, List<UsersEvents> userEvents) {
        this.title = title;
        this.location = location;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.link = link;
        this.volunteerGoal = volunteerGoal;
        this.userEvents = userEvents;
    }

    public Event(long id, String title, String location, String dateAndTime, String description, String link, int volunteerGoal, List<UsersEvents> userEvents) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.link = link;
        this.volunteerGoal = volunteerGoal;
        this.userEvents = userEvents;
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

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public List<UsersEvents> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<UsersEvents> userEvents) {
        this.userEvents = userEvents;
    }
}
