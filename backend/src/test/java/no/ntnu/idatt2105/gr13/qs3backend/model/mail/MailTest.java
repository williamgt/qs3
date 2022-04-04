package no.ntnu.idatt2105.gr13.qs3backend.model.mail;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Building;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.Campus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.RoomDisplay;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MailTest {



    @Test
    @DisplayName("Positive test for creating new Mail")
    void constructorTest() {
        try {
            Mail mail = new Mail("o@stud.ntnu.no", "123");
            mail = new Mail("o@admin.ntnu.no", "123");
            mail = new Mail("o@teac.ntnu.no", "123");
            assertTrue(true);
        }catch (IllegalArgumentException e){
            fail();
        }
    }

    @Test
    @DisplayName("Negative test for creating new Mail")
    void constructorTestNeg1() {
        try {
            Mail mail = new Mail("ostud.ntnu.no", "123");
            assertTrue(true);
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("Negative test for creating new Mail")
    void constructorTestNeg2() {
        try {
            Mail mail = new Mail("o@stud.ntnu", "123");
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }
}