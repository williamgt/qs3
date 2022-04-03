package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

public class SimpleLocation {
    private SimpleCampus campus;
    private SimpleBuilding building;
    private SimpleRoom room;
    private boolean home;

    public SimpleLocation(boolean home) {
        this.home = home;
        campus = null;
        building = null;
        room = null;
    }

    public SimpleLocation(SimpleCampus campus, SimpleBuilding building, SimpleRoom room, boolean home) {
        this.campus = campus;
        this.building = building;
        this.room = room;
        this.home = home;
    }
}
