package no.ntnu.idatt2105.gr13.qs3backend.model.location;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for Campus
 * Has id, name and list of buildings
 */
public class Campus {
    private int id;
    private String name;
    private ArrayList<Building> buildings;

    /**
     * Constructor for campus
     * Needs id and name
     * @param id
     * @param name
     */
    public Campus(int id, String name) {
        if(id < 0){
            throw new IllegalArgumentException("Id can't be less than 0");
        }
        if(name.isEmpty()){
            throw new IllegalArgumentException("Name can't be empty");
        }
        this.id = id;
        this.name = name;
        buildings = new ArrayList<>();
    }

    /**
     * Method for adding building in campus
     * Checks if already exists
     * @param building
     * @return
     */
    public boolean addBuilding(Building building){
        if(building != null && !buildings.contains(building)){
            buildings.add(building);
            return true;
        }
        return false;
    }

    /**
     * Method for adding an array of buildings to campus
     * @param building
     */
    public void addBuildings(List<Building> building){
        buildings.addAll(building);
    }

    /**
     * Getter for id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for list of buildings
     * @return
     */
    public ArrayList<Building> getBuildings() {
        return buildings;
    }
}
