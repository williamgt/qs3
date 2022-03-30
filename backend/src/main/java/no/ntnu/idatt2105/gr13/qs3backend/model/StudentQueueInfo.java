package no.ntnu.idatt2105.gr13.qs3backend.model;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Location;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.StudentUser;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a student in a queue
 */
public class StudentQueueInfo {
    private StudentUser user;
    private Location location;
    private List<Task> tasks;
    private String comment;
    private Need helpOrValidate;
    private LocalDateTime timeRegisteredInQueue;

    public StudentQueueInfo(){}

    public StudentUser getUser() {
        return user;
    }

    public Location getLocation() {
        return location;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Need getHelpOrValidate() {
        return helpOrValidate;
    }

    public LocalDateTime getTimeRegisteredInQueue() {
        return timeRegisteredInQueue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUser(StudentUser user) {
        this.user = user;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setHelpOrValidate(Need helpOrValidate) {
        this.helpOrValidate = helpOrValidate;
    }

    public void setTimeRegisteredInQueue(LocalDateTime timeRegisteredInQueue) {
        this.timeRegisteredInQueue = timeRegisteredInQueue;
    }
}
