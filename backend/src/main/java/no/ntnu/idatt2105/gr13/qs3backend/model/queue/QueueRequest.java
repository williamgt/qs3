package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskWithNums;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.UserBasicWithId;

import java.util.List;

/**
 * Models a request sent from a StudentUser that wants to queue up. Contains which course queue (in the form of the
 * course hashId) the user is queueing up for, which user is queueing up, their message, whether they are home or not,
 * information related to their location if they are not home, whether they need help or validation, their potential group
 * (group is deprecated) and which tasks they are queueing up for validation/help.
 */
public class QueueRequest {
    private String hashId;
    private UserBasicWithId user;
    private String message;
    private boolean home;
    private int campusId;
    private int buildingId;
    private int table;
    private int roomId;
    private boolean vali;
    private List<User> group;
    private List<TaskWithNums> task;

    /**
     * Instantiates a new Queue request.
     */
    public QueueRequest() {
    }

    /**
     * Gets campus id.
     *
     * @return the campus id
     */
    public int getCampusId() {
        return campusId;
    }

    /**
     * Sets campus id.
     *
     * @param campusId the campus id
     */
    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    /**
     * Gets building id.
     *
     * @return the building id
     */
    public int getBuildingId() {
        return buildingId;
    }

    /**
     * Sets building id.
     *
     * @param buildingId the building id
     */
    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    /**
     * Gets room id.
     *
     * @return the room id
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * Sets room id.
     *
     * @param roomId the room id
     */
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    /**
     * Gets hash id.
     *
     * @return the hash id
     */
    public String getHashId() {
        return hashId;
    }

    /**
     * Sets hash id.
     *
     * @param hashId the hash id
     */
    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public UserBasicWithId getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(UserBasicWithId user) {
        this.user = user;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Is home boolean.
     *
     * @return the boolean
     */
    public boolean isHome() {
        return home;
    }

    /**
     * Sets home.
     *
     * @param home the home
     */
    public void setHome(boolean home) {
        this.home = home;
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

    /**
     * Is vali boolean.
     *
     * @return the boolean
     */
    public boolean isVali() {
        return vali;
    }

    /**
     * Sets vali.
     *
     * @param vali the vali
     */
    public void setVali(boolean vali) {
        this.vali = vali;
    }

    /**
     * Gets group.
     *
     * @return the group
     */
    public List<User> getGroup() {
        return group;
    }

    /**
     * Sets group.
     *
     * @param group the group
     */
    public void setGroup(List<User> group) {
        this.group = group;
    }

    /**
     * Gets task.
     *
     * @return the task
     */
    public List<TaskWithNums> getTask() {
        return task;
    }

    /**
     * Sets task.
     *
     * @param task the task
     */
    public void setTask(List<TaskWithNums> task) {
        this.task = task;
    }
}
