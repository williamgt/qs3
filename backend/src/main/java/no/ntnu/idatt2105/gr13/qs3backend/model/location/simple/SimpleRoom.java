package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

public class SimpleRoom {
    private int id;
    private int tables;
    private String roomName;
    private int floors;

    public SimpleRoom(int id, int tables, String roomName, int floors) {
        this.id = id;
        this.tables = tables;
        this.roomName = roomName;
        this.floors = floors;
    }

    public int getId() {
        return id;
    }

    public int getTables() {
        return tables;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getFloors() {
        return floors;
    }

    @Override
    public String toString() {
        return "SimpleRoom{" +
                "id=" + id +
                ", tables=" + tables +
                ", roomName='" + roomName + '\'' +
                ", floors=" + floors +
                '}';
    }
}
