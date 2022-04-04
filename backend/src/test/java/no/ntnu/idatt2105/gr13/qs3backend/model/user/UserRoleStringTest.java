package no.ntnu.idatt2105.gr13.qs3backend.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleStringTest {
    @Test
    @DisplayName("Constructor Test")
    void constr(){
        try{
            UserRoleString userRoleString = new UserRoleString("xray@gmail.com ", "123", "Olav", "Larsen", "admin");
            assertEquals(userRoleString.getEmail(), "xray@gmail.com");
            assertEquals("ADMIN", userRoleString.getRole());
        }catch (IllegalArgumentException e){
            fail();
        }
    }
}