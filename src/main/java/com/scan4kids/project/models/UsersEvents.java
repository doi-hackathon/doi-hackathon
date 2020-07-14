package com.scan4kids.project.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table("users_events")
public class UsersEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @OneToOne
    @JoinColumn(name = "event_id")
    @JsonManagedReference
    private Event event;

    @Column(nullable = false)
    private boolean isVolunteer;

    public UsersEvents(){}

    public UsersEvents(User user, Event event, boolean isVolunteer){
        this.user = user;
        this.event = event;
        this. isVolunteer = isVolunteer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isVolunteer() {
        return isVolunteer;
    }

    public void setVolunteer(boolean volunteer) {
        isVolunteer = volunteer;
    }
}
