package no.ntnu.idatt2105.gr13.qs3backend.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void setEmail() {
    }

    @Nested
    @DisplayName("Tests for constructors")
    class constructor{

        @Test
        @DisplayName("Successful constructors")
        void RightConst(){
            try{
                User user = new User("lars@123.com","h","h","h",1);
                user = new User("lars@123.com","h","h","h");
                user = new User("lars@123.com","h","h",1);
                user = new User("lars@123.com","h","h");
                assertTrue(true);
            }catch (IllegalArgumentException e){
                fail();
            }
        }

        @Nested
        @DisplayName("Test for making sure constructor throws right exceptions")
        class fullConst{

            @Test
            @DisplayName("No @ in email")
            void emailWrong1(){
                try{
                    User user = new User("lars.com","h","h","h",1);
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }
            @Test
            @DisplayName("No end of email")
            void emailWrong2(){
                try{
                    User user = new User("lars@gmail.","h","h","h",1);
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }
            @Test
            @DisplayName("Wrong mail")
            void emailWrong3(){
                try{
                    User user = new User("asd","h","h","h",1);
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }

            @Test
            @DisplayName("First name blank")
            void firstName(){
                try{
                    User user = new User("c@gmail.com","h","","h",1);
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }

            @Test
            @DisplayName("Last name blank")
            void lastName(){
                try{
                    User user = new User("c@gmail.com","h","h","",1);
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }

            @Test
            @DisplayName("Password blank")
            void Password(){
                try{
                    User user = new User("c@gmail.com","","h","h",1);
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }
            @Test
            @DisplayName("Neg id")
            void negId(){
                try{
                    User user = new User("c@gmail.com","g","h","h", -1);
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }
        }

        @Nested
        @DisplayName("Second consturctor")
        class secondConst{
            @Test
            @DisplayName("No @ in email")
            void emailWrong1(){
                try{
                    User user = new User("lars.com","h","h");
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }
            @Test
            @DisplayName("No end of email")
            void emailWrong2(){
                try{
                    User user = new User("lars@gmail.","h","h");
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }
            @Test
            @DisplayName("Wrong mail")
            void emailWrong3(){
                try{
                    User user = new User("asd","h","h");
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }

            @Test
            @DisplayName("First name blank")
            void firstName(){
                try{
                    User user = new User("c@gmail.com","","h");
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }

            @Test
            @DisplayName("Last name blank")
            void lastName(){
                try{
                    User user = new User("c@gmail.com","h","");
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }

        }

        @Nested
        class thirdConst{
            @Test
            @DisplayName("No @ in email")
            void emailWrong1(){
                try{
                    User user = new User("lars.com","h","h", 1);
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }
            @Test
            @DisplayName("No end of email")
            void emailWrong2(){
                try{
                    User user = new User("lars@gmail.","h","h", 1);
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }
            @Test
            @DisplayName("Wrong mail")
            void emailWrong3(){
                try{
                    User user = new User("asd","h","h", 1);
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }

            @Test
            @DisplayName("First name blank")
            void firstName(){
                try{
                    User user = new User("c@gmail.com","","h", 1);
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }

            @Test
            @DisplayName("Last name blank")
            void lastName(){
                try{
                    User user = new User("c@gmail.com","h","", 1);
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }

            @Test
            @DisplayName("Neg id")
            void negId(){
                try{
                    User user = new User("c@gmail.com","h","h", -1);
                    fail();
                }catch (IllegalArgumentException e){
                    assertTrue(true);
                }
            }
        }
    }
}