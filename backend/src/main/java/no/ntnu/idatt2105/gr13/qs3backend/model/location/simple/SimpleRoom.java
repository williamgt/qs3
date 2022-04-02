package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

public class SimpleRoom {
    private String roomName;
    private int roomId;
    private int table;
    private int floor;

    public SimpleRoom(String roomName, int roomId, int table, int floor) {
        this.roomName = roomName;
        this.roomId = roomId;
        this.table = table;
        this.floor = floor;
    }
}
