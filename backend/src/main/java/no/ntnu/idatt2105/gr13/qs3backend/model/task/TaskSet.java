package no.ntnu.idatt2105.gr13.qs3backend.model.task;

import java.util.List;

public class TaskSet {
    private int amountMustDone;
    private int taskSetId;
    List<Task> tasks;

    public TaskSet() {
    }

    public int getAmountMustDone() {
        return amountMustDone;
    }

    public void setAmountMustDone(int amountMustDone) {
        this.amountMustDone = amountMustDone;
    }

    public int getTaskSetId() {
        return taskSetId;
    }

    public void setTaskSetId(int taskSetId) {
        this.taskSetId = taskSetId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
