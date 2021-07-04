package com.tohire.tohire.Models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private Date dueOn;
    private Date start;

    public Project(){}

    public Project(int id, String title, String description, Date dueOn, Date start) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueOn = dueOn;
        this.start = start;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDueOn() {
        return dueOn;
    }
    public void setDueOn(Date dueOn) {
        this.dueOn = dueOn;
    }
    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
    }

    
}
