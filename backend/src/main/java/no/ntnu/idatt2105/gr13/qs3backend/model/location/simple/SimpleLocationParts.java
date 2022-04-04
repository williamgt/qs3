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

    /**
     * Empty constructor for SimpleLocationParts
     */
    public SimpleLocationParts() {
    }

    /**
     * Constructor for SimpleLocationParts
     * Has different checks to make sure location is valid, throws IllegalArgumentException if:
     * locationId, roomId, buildingId or campusId is less than 0,
     * table is less than one,
     * roomName, buildingName or campusName is an empty String or null
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
        if(table < 0){
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


    /**
     * Sets room id.
     *
     * @param roomId the room id
     */
    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
     * Gets floor.
     *
     * @return the floor
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Sets floor.
     *
     * @param floor the floor
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * Gets building name.
     *
     * @return the building name
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Sets building name.
     *
     * @param buildingName the building name
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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
     * Gets campus name.
     *
     * @return the campus name
     */
    public String getCampusName() {
        return campusName;
    }

    /**
     * Sets campus name.
     *
     * @param campusName the campus name
     */
    public void setCampusName(String campusName) {
        this.campusName = campusName;
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
}
