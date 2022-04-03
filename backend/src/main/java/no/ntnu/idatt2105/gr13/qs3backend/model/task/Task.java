package no.ntnu.idatt2105.gr13.qs3backend.model.task;

public class Task {
    private String description; //'Task 1, Task 2 etc'

    public Task() {
    }

    public Task(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
