package no.ntnu.idatt2105.gr13.qs3backend.model.task;

public class TaskWithId extends Task {
    private int taskId;

    public TaskWithId() {
    }

    public TaskWithId(int taskId) {
        this.taskId = taskId;
        setDescription("Task " + taskId);
    }

    public TaskWithId(String description, int taskId){
        super(description);
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "TaskWithId{}" + getDescription() +" "+ getTaskId();
    }
}
