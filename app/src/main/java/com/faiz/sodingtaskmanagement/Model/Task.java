package com.faiz.sodingtaskmanagement.Model;

/**
 * Created by abdulazf on 6/9/2017.
 */

public class Task {
    //(id, name, description, dateCreated, dateUpdated).
    private long id;
    private String name, description, dateCreated, dateUpdated;

    public Task (long id, String name, String description, String dateCreated, String dateUpdated){
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;

    }

    public Task(){

    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateUpdated(String dateUpdated) {
       this.dateUpdated = dateUpdated;
    }

    public String getDateUpdated(){
        return dateUpdated;
    }

    @Override
    public String toString() {
        return "Id: " + getId() + "\n" +
                "Name: " + getName() + "\n" +
                "Description: " + getDescription() + "\n" +
                "Date Created: " + getDateCreated() + "\n" +
                "Date Updated: " + getDateUpdated();
    }
}
