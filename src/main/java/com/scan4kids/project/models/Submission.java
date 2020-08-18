package com.scan4kids.project.models;

import javax.persistence.*;

@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Team team;

    @Column
    private String submission_file;

    public Submission() {
    }

    public Submission(long id, Team team, String submission_file) {
        this.id = id;
        this.team = team;
        this.submission_file = submission_file;
    }

    public Submission(Team team, String submission_file) {
        this.team = team;
        this.submission_file = submission_file;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getSubmission_file() {
        return submission_file;
    }

    public void setSubmission_file(String submission_file) {
        this.submission_file = submission_file;
    }

}
