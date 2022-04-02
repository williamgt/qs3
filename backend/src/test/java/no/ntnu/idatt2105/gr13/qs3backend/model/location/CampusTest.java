package no.ntnu.idatt2105.gr13.qs3backend.model.location;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CampusTest {

    @Test
    void addBuildings() {
        Campus c = new Campus(1, "Test");
        Building b = new Building(1, "Test build");
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(b);
        ArrayList<RoomDisplay> roomDisplays = new ArrayList<>();
        roomDisplays.add(new RoomDisplay(1, 1, "name"));
        b.addRooms(roomDisplays);
        c.addBuildings(buildings);

        assertEquals(1, c.getBuildings().size());
        assertEquals(1, c.getBuildings().get(0).getRooms().size());
    }
}