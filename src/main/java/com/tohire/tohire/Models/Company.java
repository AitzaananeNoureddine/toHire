package com.tohire.tohire.Models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String industry;
    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Set<User> employees;
    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Set<Project> projects;

    public Company(){}

    public Company(int id, String name, String industry, Set<User> employees, Set<Project> projects) {
        this.id = id;
        this.name = name;
        this.industry = industry;
        this.employees = employees;
        this.projects = projects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Set<User> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<User> employees) {
        this.employees = employees;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    
    
}
