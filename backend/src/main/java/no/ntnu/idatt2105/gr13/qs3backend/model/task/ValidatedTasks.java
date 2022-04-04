package no.ntnu.idatt2105.gr13.qs3backend.model.task;

import java.util.List;

public class ValidatedTasks {
    private List<Task> tasks;

    public ValidatedTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
