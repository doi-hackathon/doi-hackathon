package com.scan4kids.project.models;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grade")
    private List<Team> teams;

    @Column
    private String name;

    public Grade() {
    }

    public Grade(long id, List<Team> teams, String name) {
        this.id = id;
        this.teams = teams;
        this.name = name;
    }

    public Grade(List<Team> teams, String name) {
        this.teams = teams;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
