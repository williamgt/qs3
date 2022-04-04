package no.ntnu.idatt2105.gr13.qs3backend.model.location;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomDisplayTest {

    @Nested
    @DisplayName("Tests for RoomDisplay Class")
    class constructor{

        @Test
        @DisplayName("Successful constructor")
        void constOk(){
            try{
                RoomDisplay roomDisplay = new RoomDisplay(1, 1,"Name");
                assertTrue(true);
            }catch (IllegalArgumentException e){
                fail();
            }
        }

        @Test
        @DisplayName("Test for constructor fail with wrong id")
        void constructorIdFail(){
            try{
                RoomDisplay roomDisplay = new RoomDisplay(-1, 1,"Name");
                fail();
            }catch (IllegalArgumentException e){
                assertTrue(true);
            }
        }

        @Test
        @DisplayName("Test for constructor fail with empty name")
        void constructorNameFail(){
            try{
                RoomDisplay roomDisplay = new RoomDisplay(1, 1,"");
                fail();
            }catch (IllegalArgumentException e){
                assertTrue(true);
            }
        }

        @Test
        @DisplayName("Test for constructor fail with less than 0 table")
        void constructorTableFail(){
            try{
                RoomDisplay roomDisplay = new RoomDisplay(1, 1,"");
                fail();
            }catch (IllegalArgumentException e){
                assertTrue(true);
            }
        }
    }

}