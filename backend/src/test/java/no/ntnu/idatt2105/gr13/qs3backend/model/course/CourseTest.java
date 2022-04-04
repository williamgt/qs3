package no.ntnu.idatt2105.gr13.qs3backend.model.course;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Building;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.Queue;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.StudentUser;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.TeacherUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {


    @Test
    void setYear() {
        Course course = new Course();
        try{
            course.setYear(-1);
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    void setTerm() {
        Course course = new Course();
        try{
            course.setTerm(0);
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    void setCourseCode() {
        Course course = new Course();
        String code = "hello";
        course.setCourseCode(code);
        assertEquals(course.getCourseCode(), "HELLO");
    }

    @Test
    void setCourseName() {
        Course course = new Course();
        try{
            course.setCourseName("");
            fail();
        }catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }
}