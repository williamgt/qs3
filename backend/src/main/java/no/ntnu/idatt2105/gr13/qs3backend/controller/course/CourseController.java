package no.ntnu.idatt2105.gr13.qs3backend.controller.course;


import no.ntnu.idatt2105.gr13.qs3backend.model.Course;
import no.ntnu.idatt2105.gr13.qs3backend.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/course")
//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin("*")
public class CourseController {
    Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @RequestMapping("/{course-code}")
    public Course getCourseByCourseCode(@PathVariable("course-code") String courseCode) {
        logger.info("Course with code " + courseCode + " requested.");
        Course course = courseService.getCourseByCourseCode(courseCode.trim().toUpperCase());
        if(course == null) {
            logger.info("No course with code " + courseCode + " was found in the db.");
        }else{
            logger.info("Returning course with code " + courseCode + ".");
        }
        return course;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCourse(@RequestBody Course course) {
        logger.info("Registering course with code " + course.getCourseCode() + ".");
        try {
            courseService.registerCourse(course);
            logger.info("Successfully registered course with code " + course.getCourseCode() + ".");
            return new ResponseEntity<>("Course was successfully registered.", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.info("Something went wrong when registering course: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{course-code}")
    public ResponseEntity<String> updateCourse(@PathVariable("course-code") String courseCode, @RequestBody Course course) {
        logger.info("Updating course with code " + courseCode);
        boolean updated = courseService.updateCourse(courseCode, course);
        if(updated) {
            logger.info("Successfully updated course with code" + courseCode);
            return new ResponseEntity<>("Course was updated successfully.", HttpStatus.OK);
        } else {
            logger.info("Can't update course with code " + courseCode + ", no such course found.");
            return new ResponseEntity<>("Cannot find course with code " + courseCode, HttpStatus.NOT_FOUND);
        }
    }
}
