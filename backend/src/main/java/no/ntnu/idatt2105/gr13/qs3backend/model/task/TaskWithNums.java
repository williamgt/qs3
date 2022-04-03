package no.ntnu.idatt2105.gr13.qs3backend.model.task;

public class TaskWithNums {
    private String description;
    public TaskWithNums(int num){
        description = "Task " + num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
