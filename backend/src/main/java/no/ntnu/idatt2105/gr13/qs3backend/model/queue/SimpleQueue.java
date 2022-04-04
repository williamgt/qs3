package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

/**
 * Models a simple queue, most importantly keeps track if it's id.
 */
public class SimpleQueue {
    private int queueId;
    private String description; //Message from TA
    private boolean active;

    /**
     * Instantiates a new Simple queue.
     */
    public SimpleQueue() {
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
        this.queueId = queueId;
    }
}
