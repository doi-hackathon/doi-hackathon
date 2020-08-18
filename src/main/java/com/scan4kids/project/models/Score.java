package com.scan4kids.project.models;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int score;

    @OneToOne
    private User judge;

    @ManyToOne
    @JoinColumn (name = "submission_id")
    private Submission submission;

    @Column
    private String comment;

    public Score() {
    }

    public Score(long id, int score, User judge, Submission submission, String comment) {
        this.id = id;
        this.score = score;
        this.judge = judge;
        this.submission = submission;
        this.comment = comment;
    }

    public Score(int score, User judge, Submission submission, String comment) {
        this.score = score;
        this.judge = judge;
        this.submission = submission;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getJudge() {
        return judge;
    }

    public void setJudge(User judge) {
        this.judge = judge;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
