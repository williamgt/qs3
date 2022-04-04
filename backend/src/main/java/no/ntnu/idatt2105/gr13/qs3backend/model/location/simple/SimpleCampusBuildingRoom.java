package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

/**
 * Models a simple campus building with a room
 */
public class SimpleCampusBuildingRoom {
    private int id;
    private String name;
    private SimpleBuildingRoom building;

    /**
     * Instantiates a new Simple campus building room.
     *
     * @param id       the id
     * @param name     the name
     * @param building the building
     */
    public SimpleCampusBuildingRoom(int id, String name, SimpleBuildingRoom building) {
        this.id = id;
        this.name = name;
        this.building = building;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets building.
     *
     * @return the building
     */
    public SimpleBuildingRoom getBuilding() {
        return building;
    }
}
