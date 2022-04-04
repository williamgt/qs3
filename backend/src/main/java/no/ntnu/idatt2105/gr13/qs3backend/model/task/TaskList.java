package no.ntnu.idatt2105.gr13.qs3backend.model.task;

import java.util.List;

/**
 * Models an object that contains information about the sets of tasks and total amount of tasks in a course.
 */
public class TaskList {
    private List<TaskSet> taskSets;
    private int amount;

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
    }

    /**
     * Gets task sets.
     *
     * @return the task sets
     */
    public List<TaskSet> getTaskSets() {
        return taskSets;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets task sets.
     *
     * @param taskSets the task sets
     */
    public void setTaskSets(List<TaskSet> taskSets) {
        this.taskSets = taskSets;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
