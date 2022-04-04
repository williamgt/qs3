package no.ntnu.idatt2105.gr13.qs3backend.model.task;

import java.util.List;

/**
 * Models a set containing tasks and the amount of tasks that has to be done for the set to be valid.
 */
public class TaskSet {
    private int amountMustDone;
    private int taskSetId;
    /**
     * The Tasks.
     */
    List<Task> tasks;

    /**
     * Instantiates a new Task set.
     */
    public TaskSet() {
    }

    /**
     * Gets amount must done.
     *
     * @return the amount must done
     */
    public int getAmountMustDone() {
        return amountMustDone;
    }

    /**
     * Sets amount must done.
     *
     * @param amountMustDone the amount must done
     */
    public void setAmountMustDone(int amountMustDone) {
        this.amountMustDone = amountMustDone;
    }

    /**
     * Gets task set id.
     *
     * @return the task set id
     */
    public int getTaskSetId() {
        return taskSetId;
    }

    /**
     * Sets task set id.
     *
     * @param taskSetId the task set id
     */
    public void setTaskSetId(int taskSetId) {
        this.taskSetId = taskSetId;
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
     * Sets tasks.
     *
     * @param tasks the tasks
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
