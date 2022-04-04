package no.ntnu.idatt2105.gr13.qs3backend.model.location;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CampusTest {

    @Nested
    @DisplayName("Tests for Campus Class")
    class constructor{

        @Test
        @DisplayName("Successful constructor")
        void constOk(){
            try{
                Campus campus = new Campus(1, "Name");
                assertTrue(true);
            }catch (IllegalArgumentException e){
                fail();
            }
        }

        @Test
        @DisplayName("Test for constructor fail with wrong id")
        void constructorIdFail(){
            try{
                Campus campus = new Campus(-1, "Name");
                fail();
            }catch (IllegalArgumentException e){
                assertTrue(true);
            }
        }

        @Test
        @DisplayName("Test for constructor fail with empty name")
        void constructorNameFail(){
            try{
                Campus campus = new Campus(1, "");
                fail();
            }catch (IllegalArgumentException e){
                assertTrue(true);
            }
        }
    }

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