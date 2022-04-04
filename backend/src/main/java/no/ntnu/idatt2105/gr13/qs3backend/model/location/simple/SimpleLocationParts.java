package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.BareBoneLocation;

/**
 * Model for a Simple location. Extends BareBoneLocation
 */
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

    /**
     * Constructor for SimpleLocationParts
     * Has different checks to make sure location is valid
     * @param locationId
     * @param roomName
     * @param roomId
     * @param table
     * @param floor
     * @param buildingName
     * @param buildingId
     * @param campusName
     * @param campusId
     */
    public SimpleLocationParts(int locationId, String roomName, int roomId, int table, int floor, String buildingName, int buildingId, String campusName, int campusId) {
        if(locationId < 0){
            throw new IllegalArgumentException("Location id can't be less than 0");
        }
        if(roomId < 0){
            throw new IllegalArgumentException("RoomId can't be less than 0");
        }
        if(buildingId < 0){
            throw new IllegalArgumentException("BuildingId can't be less than 0");
        }
        if(campusId < 0){
            throw new IllegalArgumentException("Campus id can't be less than 0");
        }
        if(table < 1){
            throw new IllegalArgumentException("Table can't be less than 1");
        }
        if(roomName.isEmpty()){
            throw new IllegalArgumentException("RoomName can't be empty");
        }
        if(buildingName.isEmpty()){
            throw new IllegalArgumentException("BuildingName can't be empty");
        }
        if(campusName.isEmpty()){
            throw new IllegalArgumentException("CampusName can't be empty");
        }
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

    /**
     * Getter for locationId
     * @return
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * Setter for location ID
     * @param locationId
     */
    public void setLocationId(int locationId) {
        if(locationId < 0){
            throw new IllegalArgumentException("Location id can't be less than 0");
        }
        this.locationId = locationId;
    }

    /**
     * Getter for RoomName
     * @return
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Setter for roomName
     * @param roomName
     */
    public void setRoomName(String roomName) {
        if(roomId < 0){
            throw new IllegalArgumentException("RoomId can't be less than 0");
        }
        this.roomName = roomName;
    }

    /**
     * Getter for RoomId
     * @return
     */
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
