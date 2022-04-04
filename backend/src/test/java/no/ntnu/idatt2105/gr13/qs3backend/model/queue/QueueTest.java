package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Building;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    @DisplayName("Set it with wrong id fails")
    void setQueueId() {
        try{
            Queue queue = new Queue(1, true,"Name");
            queue.setQueueId(-1);
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }
    @Nested
    @DisplayName("Tests for Queue Class constructor")
    class constructor{

        @Test
        @DisplayName("Successful constructor")
        void constOk(){
            try{
                Queue queue = new Queue(1, true,"Name");
                assertTrue(true);
            }catch (IllegalArgumentException e){
                fail();
            }
        }

        @Test
        @DisplayName("Test for constructor fail with wrong id")
        void constructorIdFail(){
            try{
                Queue queue = new Queue(-1, true,"Name");
                fail();
            }catch (IllegalArgumentException e){
                assertTrue(true);
            }
        }

    }
}