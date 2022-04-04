package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

import java.util.ArrayList;
import java.util.List;


public class Queue {
    private int queueId;
    private boolean active;
    private String description; //Message from TA
    private List<StudentQueueInfo> studsInQueue = new ArrayList<>();

    public Queue() {
    }

    public Queue(int queueId, boolean active, String description) {
        if(queueId < 0){
            throw new IllegalArgumentException("QueueId can't be less than 0");
        }
        this.queueId = queueId;
        this.active = active;
        this.description = description.trim();
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        if(queueId < 0){
            throw new IllegalArgumentException("QueueId can't be less than 0");
        }
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

    public boolean setStudsInQueue(List<StudentQueueInfo> studsInQueue) {
        this.studsInQueue = new ArrayList<>();
        return this.studsInQueue.addAll(studsInQueue);
    }
}
