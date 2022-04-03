package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

public class SimpleRoomWBC {

    private int id;
    private int tables;
    private String roomName;
    private int floors;
    private String campusName;
    private String buildingName;

    public SimpleRoomWBC(int id, int tables, String roomName, int floors) {
        this.id = id;
        this.tables = tables;
        this.roomName = roomName;
        this.floors = floors;
    }

    public SimpleRoomWBC(int id, int tables, String roomName, int floors, String campusName, String buildingName) {
        this.id = id;
        this.tables = tables;
        this.roomName = roomName;
        this.floors = floors;
        this.campusName = campusName;
        this.buildingName = buildingName;
    }

    public String getCampusName() {
        return campusName;
    }

    public String getBuildingName() {
        return buildingName;
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
}
