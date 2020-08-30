package com.scan4kids.project.models;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    public Grade() {
    }

    public Grade(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Grade(String name) {
        this.name = name;
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
}
