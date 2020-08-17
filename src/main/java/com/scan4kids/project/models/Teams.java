package com.scan4kids.project.models;

import javax.persistence.*;

@Entity
@Table(name = "teams")

public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false)
    private String name;






}
