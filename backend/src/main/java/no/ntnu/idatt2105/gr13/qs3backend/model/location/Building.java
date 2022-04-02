package no.ntnu.idatt2105.gr13.qs3backend.model.location;

import java.util.ArrayList;
import java.util.List;

public class Building {
    int id;
    String name;
    ArrayList<RoomDisplay> rooms;

    public Building(int id, String name) {
        this.id = id;
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public ArrayList<RoomDisplay> getRooms() {
        return rooms;
    }

    public boolean addRooms(List<RoomDisplay> rooms){
        this.rooms.addAll(rooms);
        return true;
    }
}
