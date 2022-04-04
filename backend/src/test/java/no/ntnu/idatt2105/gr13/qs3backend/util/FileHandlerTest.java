package no.ntnu.idatt2105.gr13.qs3backend.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    @Test
    void randomPassword() {
        assertEquals(15, FileHandler.getRandomPassword().length());
        assertTrue(FileHandler.getRandomPassword().length() != 0);
    }

}