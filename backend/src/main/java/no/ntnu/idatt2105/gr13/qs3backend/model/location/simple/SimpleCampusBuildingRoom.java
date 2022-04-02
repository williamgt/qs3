package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

import java.util.ArrayList;

public class SimpleCampusBuildingRoom {
    private int id;
    private String name;
    private SimpleBuildingRoom building;

    public SimpleCampusBuildingRoom(int id, String name, SimpleBuildingRoom building) {
        this.id = id;
        this.name = name;
        this.building = building;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SimpleBuildingRoom getBuilding() {
        return building;
    }
}
