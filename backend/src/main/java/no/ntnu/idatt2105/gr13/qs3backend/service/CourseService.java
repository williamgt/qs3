package no.ntnu.idatt2105.gr13.qs3backend.service;

import no.ntnu.idatt2105.gr13.qs3backend.model.Course;
import no.ntnu.idatt2105.gr13.qs3backend.repository.JdbcCourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private JdbcCourseRepository courseRepo;

    public boolean updateCourse(String courseCode, Course course) { //TODO add checks and trim strings etc
        Course getCourse = courseRepo.getCourseByCode(courseCode);
        if(getCourse == null) {
            logger.info("Not able to update course with code " + courseCode + ", not found in db");
            return false;
        }
        else { //TODO add checks
            return courseRepo.updateCourse(courseCode, course);
            return false;
        }
    }

    public void registerCourse(Course course) {
        logger.info("Trying to register a new course...");
        //courseRepo.registerCourse(course);
    }

    public Course getCourseByCourseCode(String courseCode) {
        return courseRepo.getCourseByCode(courseCode);
    }
}
