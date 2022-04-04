package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.Need;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleLocation;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.StudentUser;

import java.sql.Date;
import java.util.List;

/**
 * Models an instance of a student that has queued up.
 */
public class StudentQueueInfo {
    private StudentUser user;
    private SimpleLocation location;
    private List<Task> tasks;
    private String comment;
    private Need helpOrValidate;
    private Date timeRegisteredInQueue;

    /**
     * Instantiates a new Student queue info.
     */
    public StudentQueueInfo(){}

    /**
     * Gets user.
     *
     * @return the user
     */
    public StudentUser getUser() {
        return user;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public SimpleLocation getLocation() {
        return location;
    }

    /**
     * Gets tasks.
     *
     * @return the tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets help or validate.
     *
     * @return the help or validate
     */
    public Need getHelpOrValidate() {
        return helpOrValidate;
    }

    /**
     * Gets time registered in queue.
     *
     * @return the time registered in queue
     */
    public Date getTimeRegisteredInQueue() {
        return timeRegisteredInQueue;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(StudentUser user) {
        this.user = user;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(SimpleLocation location) {
        this.location = location;
    }

    /**
     * Sets tasks.
     *
     * @param tasks the tasks
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Sets help or validate.
     *
     * @param helpOrValidate the help or validate
     */
    public void setHelpOrValidate(Need helpOrValidate) {
        this.helpOrValidate = helpOrValidate;
    }

    /**
     * Sets time registered in queue.
     *
     * @param timeRegisteredInQueue the time registered in queue
     */
    public void setTimeRegisteredInQueue(Date timeRegisteredInQueue) {
        this.timeRegisteredInQueue = timeRegisteredInQueue;
    }
}
