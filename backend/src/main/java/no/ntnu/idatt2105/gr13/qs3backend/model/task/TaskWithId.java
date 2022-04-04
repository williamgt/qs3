package no.ntnu.idatt2105.gr13.qs3backend.model.task;

/**
 * Models a task with ID from the DB.
 */
public class TaskWithId extends Task {
    private int taskId;

    /**
     * Instantiates a new Task with id.
     */
    public TaskWithId() {
    }

    /**
     * Instantiates a new Task with id.
     *
     * @param description the description
     * @param taskId      the task id
     */
    public TaskWithId(String description, int taskId){
        super(description);
        this.taskId = taskId;
    }

    /**
     * Gets task id.
     *
     * @return the task id
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * Sets task id.
     *
     * @param taskId the task id
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

}
