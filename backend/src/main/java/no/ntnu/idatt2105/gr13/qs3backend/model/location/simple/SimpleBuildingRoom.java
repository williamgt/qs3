package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

import java.util.ArrayList;

/**
 * Models all the rooms in a building with a given ID and name.
 */
public class SimpleBuildingRoom {
    private int id;
    private String name;
    private ArrayList<SimpleRoom> rooms;

    /**
     * Instantiates a new Simple building room.
     *
     * @param id   the id
     * @param name the name
     */
    public SimpleBuildingRoom(int id, String name) {
        this.id = id;
        this.name = name;
        rooms = new ArrayList<>();
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
     * Gets rooms.
     *
     * @return the rooms
     */
    public ArrayList<SimpleRoom> getRooms() {
        return rooms;
    }

    /**
     * Add rooms.
     *
     * @param rooms the rooms
     */
    public void addRooms(ArrayList<SimpleRoom> rooms){
        this.rooms.addAll(rooms);
    }
}
