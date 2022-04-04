package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.BareBoneLocation;

public class SimpleLocationParts extends BareBoneLocation {
    private int locationId;
    private String roomName;
    private int roomId;
    private int table;
    private int floor;
    private String buildingName;
    private int buildingId;
    private String campusName;
    private int campusId;

    public SimpleLocationParts() {
    }

    public SimpleLocationParts(int locationId, String roomName, int roomId, int table, int floor, String buildingName, int buildingId, String campusName, int campusId) {
        this.locationId = locationId;
        this.roomName = roomName;
        this.roomId = roomId;
        this.table = table;
        this.floor = floor;
        this.buildingName = buildingName;
        this.buildingId = buildingId;
        this.campusName = campusName;
        this.campusId = campusId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public int getCampusId() {
        return campusId;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    @Override
    public String toString() {
        return "SimpleLocationParts{" +
                "locationId=" + locationId +
                ", roomName='" + roomName + '\'' +
                ", roomId=" + roomId +
                ", table=" + table +
                ", floor=" + floor +
                ", buildingName='" + buildingName + '\'' +
                ", buildingId=" + buildingId +
                ", campusName='" + campusName + '\'' +
                ", campusId=" + campusId +
                '}';
    }
}
