package no.ntnu.idatt2105.gr13.qs3backend.model.location;

import java.util.ArrayList;
import java.util.List;

public class Building {
    int id;
    String buildingName;
    ArrayList<RoomDisplay> rooms;

    public Building(int id, String buildingName) {
        this.id = id;
        this.buildingName = buildingName;
        this.rooms = new ArrayList<>();
    }

    public int getId() {
        return id;
    }


    public String getBuildingName() {
        return buildingName;
    }

    public ArrayList<RoomDisplay> getRooms() {
        return rooms;
    }

    public boolean addRooms(List<RoomDisplay> rooms){
        this.rooms.addAll(rooms);
        return true;
    }
}
