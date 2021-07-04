package com.tohire.tohire.Models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private Date dueOn;
    private Date start;
    @JsonManagedReference
    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Task> tasks;
    @JsonManagedReference
    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Set<User> teamMemebers;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Company company;

    public Project(){}

    public Project(int id, String title, String description, Date dueOn, Date start, Set<Task> tasks,
            Set<User> teamMemebers, Company company) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueOn = dueOn;
        this.start = start;
        this.tasks = tasks;
        this.teamMemebers = teamMemebers;
        this.company = company;
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<User> getTeamMemebers() {
        return teamMemebers;
    }

    public void setTeamMemebers(Set<User> teamMemebers) {
        this.teamMemebers = teamMemebers;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    
    
}
