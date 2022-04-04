package no.ntnu.idatt2105.gr13.qs3backend.model.location;

import java.util.ArrayList;
import java.util.List;

/**
 * Class model representing Building
 */
public class Building {
    int id;
    String name;
    ArrayList<RoomDisplay> rooms;

    /**
     * Constructor. Needs Id and name
     * @param id
     * @param name
     */
    public Building(int id, String name) {
        if(id < 0){
            throw new IllegalArgumentException("Id can't be less than 0");
        }
        if(name.isEmpty()){
            throw new IllegalArgumentException("Name can't be empty");
        }
        this.id = id;
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    /**
     * Getter for Id
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
     * Getter for rooms
     * @return
     */
    public ArrayList<RoomDisplay> getRooms() {
        return rooms;
    }

    /**
     * Adds room to list of rooms in building
     * @param rooms
     */
    public void addRooms(List<RoomDisplay> rooms){
        this.rooms.addAll(rooms);
    }
}
