package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

import java.sql.Date;

public class SimpleQueueInfo {
    private int queueInfoId;
    private int validate;
    private Date startDate;
    private int active;
    private int locationId;
    private String comment;
    private int queueId;
    private int table;

    public SimpleQueueInfo() {
    }

    public int getQueueInfoId() {
        return queueInfoId;
    }

    public void setQueueInfoId(int queueInfoId) {
        this.queueInfoId = queueInfoId;
    }

    public int getValidate() {
        return validate;
    }

    public void setValidate(int validate) {
        this.validate = validate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }
}
