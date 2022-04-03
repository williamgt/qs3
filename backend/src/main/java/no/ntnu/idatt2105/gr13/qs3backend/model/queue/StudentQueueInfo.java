package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.Need;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleLocation;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.StudentUser;

import java.sql.Date;
import java.util.List;

/**
 * Represents a student in a queue
 */
public class StudentQueueInfo {
    private StudentUser user;
    private SimpleLocation location;
    private List<Task> tasks;
    private String comment;
    private Need helpOrValidate;
    private Date timeRegisteredInQueue;

    public StudentQueueInfo(){}

    public StudentUser getUser() {
        return user;
    }

    public SimpleLocation getLocation() {
        return location;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Need getHelpOrValidate() {
        return helpOrValidate;
    }

    public Date getTimeRegisteredInQueue() {
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

    public void setLocation(SimpleLocation location) {
        this.location = location;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setHelpOrValidate(Need helpOrValidate) {
        this.helpOrValidate = helpOrValidate;
    }

    public void setTimeRegisteredInQueue(Date timeRegisteredInQueue) {
        this.timeRegisteredInQueue = timeRegisteredInQueue;
    }
}
