package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

/**
 * Models a Building with its name and corresponding ID found in DB
 */
public class SimpleBuilding {
    private String buildingName;
    private int buildingId;

    /**
     * Instantiates a new Simple building.
     *
     * @param buildingName the building name
     * @param buildingId   the building id
     */
    public SimpleBuilding(String buildingName, int buildingId) {
        this.buildingName = buildingName;
        this.buildingId = buildingId;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return buildingId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return buildingName;
    }
}
