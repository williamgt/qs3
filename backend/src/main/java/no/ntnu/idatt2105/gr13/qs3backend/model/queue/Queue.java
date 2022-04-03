package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

import java.util.List;


public class Queue {
    private int queueId;
    private boolean active;
    private String description; //Message from TA
    private List<StudentQueueInfo> studsInQueue;

    public Queue() {
    }

    public Queue(int queueId, boolean active, String description) {
        this.queueId = queueId;
        this.active = active;
        this.description = description;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
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

    public List<StudentQueueInfo> getStudsInQueue() {
        return studsInQueue;
    }

    public void setStudsInQueue(List<StudentQueueInfo> studsInQueue) {
        this.studsInQueue = studsInQueue;
    }
}
