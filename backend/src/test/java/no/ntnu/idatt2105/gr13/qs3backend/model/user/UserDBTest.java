package no.ntnu.idatt2105.gr13.qs3backend.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDBTest {
    @Test
    @DisplayName("First constructor test wrong email test")
    void firstConstWrongID(){
        try{
            UserDB userDB = new UserDB("asdasd@gmail.com", -1);
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("Second constructor test wrong email test")
    void SecondConstWrongID(){
        try{
            UserDB userDB = new UserDB("asdasd@gmail.com", "asdas", -1);
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("First Constructor works")
    void firstConstSuccess(){
        try{
            UserDB userDB = new UserDB("asdasd@gmail.com", 11);

            assertTrue(true);
        }catch (IllegalArgumentException e){
            fail();
        }
    }

    @Test
    @DisplayName("Second constructor works")
    void SecondConstSuccess(){
        try{
            UserDB userDB = new UserDB("asdasd@gmail.com", "asdas", 1);
            assertTrue(true);
        }catch (IllegalArgumentException e){
            fail();
        }
    }

}