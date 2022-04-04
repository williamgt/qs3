package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

/**
 * Model for a simple room with info about campus and building ids
 * For Json simplicity
 */
public class SimpleRoomWBC{
    private int id;
    private int tables;
    private String roomName;
    private int floors;
    private String campusName;
    private String buildingName;

    /**
     * Constructor for SimpleRoomWBC, creating an instance of a representation of a room along with
     * the name of the campus and building it is in.
     * @param id
     * @param tables
     * @param roomName
     * @param floors
     * @param campusName
     * @param buildingName
     */
    public SimpleRoomWBC(int id, int tables, String roomName, int floors, String campusName, String buildingName) {
        this.id = id;
        this.tables = tables;
        this.roomName = roomName;
        this.floors = floors;
        this.campusName = campusName;
        this.buildingName = buildingName;
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
     * Gets building name.
     *
     * @return the building name
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets tables.
     *
     * @return the tables
     */
    public int getTables() {
        return tables;
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
}
