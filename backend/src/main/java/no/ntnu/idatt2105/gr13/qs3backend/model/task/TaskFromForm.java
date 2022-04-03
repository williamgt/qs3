package no.ntnu.idatt2105.gr13.qs3backend.model.task;

public class TaskFromForm {
    private String task; //Task 1, task 2 etc
    private boolean checked;
    private String id;

    public TaskFromForm() {
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
