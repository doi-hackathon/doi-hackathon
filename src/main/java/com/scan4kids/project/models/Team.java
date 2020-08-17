package com.scan4kids.project.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")

public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn (name = "grade_id")
    private Grade grade;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private List<User> teammates;

    public Team() {
    }

    public Team(String name, Grade grade, List<User> teammates) {
        this.name = name;
        this.grade = grade;
        this.teammates = teammates;
    }

    public Team(long id, String name, Grade grade, List<User> teammates) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.teammates = teammates;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public List<User> getTeammates() {
        return teammates;
    }

    public void setTeammates(List<User> teammates) {
        this.teammates = teammates;
    }
}
