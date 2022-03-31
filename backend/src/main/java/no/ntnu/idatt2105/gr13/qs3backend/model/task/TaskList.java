package no.ntnu.idatt2105.gr13.qs3backend.model.task;

import java.util.List;

public class TaskList {
    private List<TaskSet> taskSets;
    private int amount;

    public TaskList() {
    }

    public List<TaskSet> getTaskSets() {
        return taskSets;
    }

    public int getAmount() {
        return amount;
    }

    public void setTaskSets(List<TaskSet> taskSets) {
        this.taskSets = taskSets;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
