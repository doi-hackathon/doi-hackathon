package com.scan4kids.project.models;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int dataManagement;

    @Column
    private int creativity;

    @Column
    private int analysis;

    @Column
    private int visualization;

    @Column
    private int coding;

    @Column
    private int professionalism;

    @Column
    private int totalScore;

    @OneToOne
    private User judge;

    @ManyToOne
    @JoinColumn (name = "submission_id")
    private Submission submission;

    @Column
    private String comment;

    public Score() {
    }

    public Score(long id, int dataManagement, int creativity, int analysis, int visualization, int coding, int professionalism, int totalScore, User judge, Submission submission, String comment) {
        this.id = id;
        this.dataManagement = dataManagement;
        this.creativity = creativity;
        this.analysis = analysis;
        this.visualization = visualization;
        this.coding = coding;
        this.professionalism = professionalism;
        this.totalScore = totalScore;
        this.judge = judge;
        this.submission = submission;
        this.comment = comment;
    }

    public Score(int dataManagement, int creativity, int analysis, int visualization, int coding, int professionalism, int totalScore, User judge, Submission submission, String comment) {
        this.dataManagement = dataManagement;
        this.creativity = creativity;
        this.analysis = analysis;
        this.visualization = visualization;
        this.coding = coding;
        this.professionalism = professionalism;
        this.totalScore = totalScore;
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

    public int getDataManagement() {
        return dataManagement;
    }

    public void setDataManagement(int dataManagement) {
        this.dataManagement = dataManagement;
    }

    public int getCreativity() {
        return creativity;
    }

    public void setCreativity(int creativity) {
        this.creativity = creativity;
    }

    public int getAnalysis() {
        return analysis;
    }

    public void setAnalysis(int analysis) {
        this.analysis = analysis;
    }

    public int getVisualization() {
        return visualization;
    }

    public void setVisualization(int visualization) {
        this.visualization = visualization;
    }

    public int getCoding() {
        return coding;
    }

    public void setCoding(int coding) {
        this.coding = coding;
    }

    public int getProfessionalism() {
        return professionalism;
    }

    public void setProfessionalism(int professionalism) {
        this.professionalism = professionalism;
    }

    public int getTotalScore() {
        return dataManagement + creativity + analysis + visualization + coding + professionalism;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
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
