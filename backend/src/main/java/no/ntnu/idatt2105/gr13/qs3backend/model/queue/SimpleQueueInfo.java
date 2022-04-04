package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

import java.sql.Date;

/**
 * Models information related to a student in the queue, like their location, when they queued up, the queue positions
 * ID and the ID of the overall queue.
 */
public class SimpleQueueInfo {
    private int queueInfoId;
    private int validate;
    private Date startDate;
    private int active;
    private int locationId;
    private String comment;
    private int queueId;
    private int table;

    /**
     * Instantiates a new Simple queue info.
     */
    public SimpleQueueInfo() {
    }

    /**
     * Gets queue info id.
     *
     * @return the queue info id
     */
    public int getQueueInfoId() {
        return queueInfoId;
    }

    /**
     * Sets queue info id.
     *
     * @param queueInfoId the queue info id
     */
    public void setQueueInfoId(int queueInfoId) {
        this.queueInfoId = queueInfoId;
    }

    /**
     * Gets validate.
     *
     * @return the validate
     */
    public int getValidate() {
        return validate;
    }

    /**
     * Sets validate.
     *
     * @param validate the validate
     */
    public void setValidate(int validate) {
        this.validate = validate;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets active.
     *
     * @return the active
     */
    public int getActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(int active) {
        this.active = active;
    }

    /**
     * Gets location id.
     *
     * @return the location id
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * Sets location id.
     *
     * @param locationId the location id
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
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

    /**
     * Gets table.
     *
     * @return the table
     */
    public int getTable() {
        return table;
    }

    /**
     * Sets table.
     *
     * @param table the table
     */
    public void setTable(int table) {
        this.table = table;
    }
}
