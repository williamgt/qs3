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

    public SimpleCampus getCampus() {
        return campus;
    }

    public void setCampus(SimpleCampus campus) {
        this.campus = campus;
    }

    public SimpleBuilding getBuilding() {
        return building;
    }

    public void setBuilding(SimpleBuilding building) {
        this.building = building;
    }

    public SimpleRoom getRoom() {
        return room;
    }

    public void setRoom(SimpleRoom room) {
        this.room = room;
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "SimpleLocation{" +
                "campus=" + campus +
                ", building=" + building +
                ", room=" + room +
                ", home=" + home +
                '}';
    }
}
