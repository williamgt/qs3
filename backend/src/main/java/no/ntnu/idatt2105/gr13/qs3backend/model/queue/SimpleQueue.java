package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

public class SimpleQueue {
    private int queueId;
    private String description; //Message from TA
    private boolean active;

    public SimpleQueue() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }
}
