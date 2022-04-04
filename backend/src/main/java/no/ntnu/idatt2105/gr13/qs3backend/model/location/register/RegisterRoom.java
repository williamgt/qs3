package no.ntnu.idatt2105.gr13.qs3backend.model.location.register;

/**
 * Models a room that is to be registered. A room is associated with a building nas is at a given floor
 * with a set amount of tables
 */
public class RegisterRoom {
    private int buildingId;
    private String roomName;
    private int floors;
    private int tables;

    /**
     * Instantiates a new Register room.
     *
     * @param buildingId the building id
     * @param roomName   the room name
     * @param floors     the floors
     * @param tables     the tables
     */
    public RegisterRoom(int buildingId, String roomName, int floors, int tables) {
        this.buildingId = buildingId;
        this.roomName = roomName;
        this.floors = floors;
        this.tables = tables;
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
     * Gets room name.
     *
     * @return the room name
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Gets floors.
     *
     * @return the floors
     */
    public int getFloors() {
        return floors;
    }

    /**
     * Gets tables.
     *
     * @return the tables
     */
    public int getTables() {
        return tables;
    }
}
