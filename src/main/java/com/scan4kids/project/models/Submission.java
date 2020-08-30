package com.scan4kids.project.models;

import javax.persistence.*;

@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String submission_file;

    public Submission() {
    }

    public Submission(long id, String submission_file) {
        this.id = id;
        this.submission_file = submission_file;
    }

    public Submission(String submission_file) {
        this.submission_file = submission_file;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubmission_file() {
        return submission_file;
    }

    public void setSubmission_file(String submission_file) {
        this.submission_file = submission_file;
    }

}
