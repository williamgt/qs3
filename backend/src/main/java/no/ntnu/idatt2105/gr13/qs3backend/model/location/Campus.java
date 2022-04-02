package no.ntnu.idatt2105.gr13.qs3backend.model.location;

import java.util.ArrayList;
import java.util.List;

public class Campus {
    private int id;
    private String name;
    private ArrayList<Building> buildings;

    public Campus(int id, String name) {
        this.id = id;
        this.name = name;
        buildings = new ArrayList<>();
    }

    public boolean addBuilding(Building building){
        if(building != null && !buildings.contains(building)){
            buildings.add(building);
            return true;
        }
        return false;
    }

    public void addBuildings(List<Building> building){
        buildings.addAll(building);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }
}
