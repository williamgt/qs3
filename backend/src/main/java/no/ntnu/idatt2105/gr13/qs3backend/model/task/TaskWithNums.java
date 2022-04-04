package no.ntnu.idatt2105.gr13.qs3backend.model.task;

/**
 * Models a task with a description, that being 'Task x' where x is some positive integer.
 */
public class TaskWithNums {
    private String description;

    /**
     * Instantiates a new Task with nums.
     *
     * @param num the num
     */
    public TaskWithNums(int num){
        description = "Task " + num;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
