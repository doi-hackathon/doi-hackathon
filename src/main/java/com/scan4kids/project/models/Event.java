package com.scan4kids.project.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
    private Timestamp dateAndTime;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(nullable = true)
    private String link;

    @Column(nullable = true)
    private int volunteerGoal;

    @ManyToMany(mappedBy = "events")
    private List<User> users;

    public Event(){}

    public Event(String title, String location, Timestamp dateAndTime, String description, String link, int volunteerGoal, List<User> users) {
        this.title = title;
        this.location = location;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.link = link;
        this.volunteerGoal = volunteerGoal;
        this.users = users;
    }

    public Event(long id, String title, String location, Timestamp dateAndTime, String description, String link, int volunteerGoal, List<User> users) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.link = link;
        this.volunteerGoal = volunteerGoal;
        this.users = users;
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

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getFormattedDate() {
        String s = new SimpleDateFormat("MM/dd/yyyy").format(getDateAndTime());
        return s;
    }

}
