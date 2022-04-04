package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Models a queue, and keeps track of whether it's active or not including a message from the teaching assistant.
 * The students that have queued up are also kept track of.
 */
public class Queue {
    private int queueId;
    private boolean active;
    private String description; //Message from TA
    private List<StudentQueueInfo> studsInQueue = new ArrayList<>();

    /**
     * Instantiates a new Queue.
     */
    public Queue() {
    }

    /**
     * Instantiates a new Queue.
     *
     * @param queueId     the queue id
     * @param active      the active
     * @param description the description
     */
    public Queue(int queueId, boolean active, String description) {
        if(queueId < 0){
            throw new IllegalArgumentException("QueueId can't be less than 0");
        }
        this.queueId = queueId;
        this.active = active;
        this.description = description;
    }

    /**
     * Gets queue id.
     *
     * @return the queue id
     */
    public int getQueueId() {
        return queueId;
    }

    /**
     * Sets queue id.
     *
     * @param queueId the queue id
     */
    public void setQueueId(int queueId) {
        if(queueId < 0){
            throw new IllegalArgumentException("QueueId can't be less than 0");
        }
        this.queueId = queueId;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets studs in queue.
     *
     * @return the studs in queue
     */
    public List<StudentQueueInfo> getStudsInQueue() {
        return studsInQueue;
    }

    /**
     * Sets studs in queue.
     *
     * @param studsInQueue the studs in queue
     * @return the studs in queue
     */
    public boolean setStudsInQueue(List<StudentQueueInfo> studsInQueue) {
        this.studsInQueue = new ArrayList<>();
        return this.studsInQueue.addAll(studsInQueue);
    }
}
