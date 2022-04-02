package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.RoomDisplay;

import java.util.ArrayList;

public class SimpleBuildingRoom {
    private int id;
    private String name;
    private ArrayList<SimpleRoom> rooms;

    public SimpleBuildingRoom(int id, String name) {
        this.id = id;
        this.name = name;
        rooms = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<SimpleRoom> getRooms() {
        return rooms;
    }

    public void addRooms(ArrayList<SimpleRoom> rooms){
        this.rooms.addAll(rooms);
    }
}
