package no.ntnu.idatt2105.gr13.qs3backend.model.location;

public class Location {
    private Campus campus;
    private Building building;
    private Room room;
    private boolean home = false;

    public Location(boolean home) {
        this.home = home;
        this.campus = null;
        this.building = null;
        this.room = null;
    }

    public Location(Campus campus, Building building, Room room, boolean home) {
        this.campus = campus;
        this.building = building;
        this.room = room;
        this.home = home;
    }
}
