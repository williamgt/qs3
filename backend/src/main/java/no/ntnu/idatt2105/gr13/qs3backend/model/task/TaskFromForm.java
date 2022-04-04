package no.ntnu.idatt2105.gr13.qs3backend.model.task;

/**
 * Models a task from a form from frontend, and contains information such as whether it has been done and its id
 */
public class TaskFromForm {
    private String task; //Task 1, task 2 etc
    private boolean checked;
    private String id;

    /**
     * Instantiates a new Task from form.
     */
    public TaskFromForm() {
    }

    /**
     * Gets task.
     *
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * Sets task.
     *
     * @param task the task
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Is checked boolean.
     *
     * @return the boolean
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * Sets checked.
     *
     * @param checked the checked
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }
}
