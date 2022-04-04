package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleBuilding;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleCampus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleRoom;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskWithNums;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.UserBasicWithId;

import java.util.List;

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

    public QueueRequest() {
    }

    public int getCampusId() {
        return campusId;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public UserBasicWithId getUser() {
        return user;
    }

    public void setUser(UserBasicWithId user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }



    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }



    public boolean isVali() {
        return vali;
    }

    public void setVali(boolean vali) {
        this.vali = vali;
    }

    public List<User> getGroup() {
        return group;
    }

    public void setGroup(List<User> group) {
        this.group = group;
    }

    public List<TaskWithNums> getTask() {
        return task;
    }

    public void setTask(List<TaskWithNums> task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "QueueRequest{" +
                "hashId="+hashId+
                "user="+user.getFirstname()+user.getLastname()+user.getEmail()+user.getId()+ '\'' +
                "message='" + message + '\'' +
                ", home=" + home +
                ", campus='" + campusId + '\'' +
                ", table=" + table +
                ", room='" + roomId + '\'' +
                ", vali=" + vali +
                ", group=" + group +
                ", task=" + task +
                '}';
    }
}
