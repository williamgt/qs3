package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

import java.util.ArrayList;

/**
 * Models buildings connected to a campus with a given ID and name
 */
public class SimpleCampusBuilding {
    private int id;
    private String name;
    private ArrayList<SimpleBuilding> buildings;

    /**
     * Instantiates a new Simple campus building with an empty list of buildings.
     *
     * @param id   the id
     * @param name the name
     */
    public SimpleCampusBuilding(int id, String name) {
        this.id = id;
        this.name = name;
        this.buildings = new ArrayList<>();
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
     * Gets buildings.
     *
     * @return the buildings
     */
    public ArrayList<SimpleBuilding> getBuildings() {
        return buildings;
    }

    /**
     * Add buildings.
     *
     * @param b the b
     */
    public void addBuildings(ArrayList<SimpleBuilding> b){
        buildings.addAll(b);
    }
}
