package no.ntnu.idatt2105.gr13.qs3backend.model.location;

public class RoomDisplay {
    private int roomId;
    private int tableAmount;
    private String roomName;

    public RoomDisplay(int roomId, int tableAmount, String roomName) {
        this.roomId = roomId;
        this.tableAmount = tableAmount;
        this.roomName = roomName;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getTableAmount() {
        return tableAmount;
    }

    public String getRoomName() {
        return roomName;
    }
}
