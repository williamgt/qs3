package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Building;

import java.util.ArrayList;

public class SimpleCampusBuilding {
    private int id;
    private String name;
    private ArrayList<SimpleBuilding> buildings;

    public SimpleCampusBuilding(int id, String name) {
        this.id = id;
        this.name = name;
        this.buildings = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<SimpleBuilding> getBuildings() {
        return buildings;
    }

    public void addBuildings(ArrayList<SimpleBuilding> b){
        buildings.addAll(b);
    }


}
