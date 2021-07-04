package com.tohire.tohire.Models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private Date start;
    private Date dueOn;
    private String status;
    private String tagName;
    private String tagColor;
    
    public Task(int id, String title, String description, Date start, Date dueOn, String status, String tagName,
            String tagColor) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.start = start;
        this.dueOn = dueOn;
        this.status = status;
        this.tagName = tagName;
        this.tagColor = tagColor;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getDueOn() {
        return dueOn;
    }

    public void setDueOn(Date dueOn) {
        this.dueOn = dueOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }

}
