package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;

import java.util.List;

public class QueueRequest {
    private String message;
    private boolean home;
    private String campus;
    private int table;
    private String room;
    private boolean vali;
    private List<User> group;
    private List<Task> task;

    public QueueRequest() {
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

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
}
