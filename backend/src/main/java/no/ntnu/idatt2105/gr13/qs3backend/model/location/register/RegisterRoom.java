package no.ntnu.idatt2105.gr13.qs3backend.model.location.register;

public class RegisterRoom {
    private int buildingId;
    private String roomName;
    private int floors;
    private int tables;

    public RegisterRoom(int buildingId, String roomName, int floors, int tables) {
        this.buildingId = buildingId;
        this.roomName = roomName;
        this.floors = floors;
        this.tables = tables;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getFloors() {
        return floors;
    }

    public int getTables() {
        return tables;
    }
}
