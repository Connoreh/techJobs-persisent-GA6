package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {
    @Size(min=3,max=50)
    @NotNull
    public String description;

@ManyToMany(mappedBy = "skills")
//@NotNull
private  List<Job> jobs = new ArrayList<>();

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Skill(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public Skill() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}