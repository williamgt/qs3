package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

public class SimpleLocation {
    private SimpleCampus campus;
    private SimpleBuilding building;
    private SimpleRoom room;
    private boolean home;

    /**
     * Instantiates a new Simple location.
     * Used mainly to instantiate SimpleLocation for Student that is home.
     *
     * @param home the home
     */
    public SimpleLocation(boolean home) {
        this.home = home;
        campus = null;
        building = null;
        room = null;
    }

    /**
     * Instantiates a new Simple location.
     *
     * @param campus   the campus
     * @param building the building
     * @param room     the room
     * @param home     the home
     */
    public SimpleLocation(SimpleCampus campus, SimpleBuilding building, SimpleRoom room, boolean home) {
        this.campus = campus;
        this.building = building;
        this.room = room;
        this.home = home;
    }

    /**
     * Gets campus.
     *
     * @return the campus
     */
    public SimpleCampus getCampus() {
        return campus;
    }

    /**
     * Sets campus.
     *
     * @param campus the campus
     */
    public void setCampus(SimpleCampus campus) {
        this.campus = campus;
    }

    /**
     * Gets building.
     *
     * @return the building
     */
    public SimpleBuilding getBuilding() {
        return building;
    }

    /**
     * Sets building.
     *
     * @param building the building
     */
    public void setBuilding(SimpleBuilding building) {
        this.building = building;
    }

    /**
     * Gets room.
     *
     * @return the room
     */
    public SimpleRoom getRoom() {
        return room;
    }

    /**
     * Sets room.
     *
     * @param room the room
     */
    public void setRoom(SimpleRoom room) {
        this.room = room;
    }

    /**
     * Is home boolean.
     *
     * @return the boolean
     */
    public boolean isHome() {
        return home;
    }

    /**
     * Sets home.
     *
     * @param home the home
     */
    public void setHome(boolean home) {
        this.home = home;
    }
}
