package no.ntnu.idatt2105.gr13.qs3backend.service;

import no.ntnu.idatt2105.gr13.qs3backend.model.course.Course;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.CourseForm;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.SimpleCourseWithName;
import no.ntnu.idatt2105.gr13.qs3backend.repository.JdbcCourseRepository;
import no.ntnu.idatt2105.gr13.qs3backend.repository.JdbcUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private JdbcCourseRepository courseRepo;

    @Autowired
    private JdbcUserRepository userRepo;

    public int updateCourse(String courseCode, Course course) { //TODO add checks and trim strings etc
        Course getCourse = courseRepo.getCourseByCode(courseCode);
        if(getCourse == null) {
            logger.info("Not able to update course with code " + courseCode + ", not found in db");
            return -1;
        }
        else { //TODO add checks
            return courseRepo.updateCourse(courseCode, course);
        }
    }

    /**
     * Registers a course and Users of different types. Each call inside the method is tagged with Transactional,
     * but registerCourse in its entirety is NOT transactional. This is a weakness. Could have put functionality
     * for registering Users of various kinds in insertCourse from JdbcCourseRepository, but it fits better in
     * JdbcUserRepository.
     * @param course
     */
    public int registerCourse(CourseForm course) {
        logger.info("Registering Students...");
        int rowsAffectedStuds = userRepo.registerStudentUsers(course.getStudents());
        logger.info("Registering Teachers...");
        int rowsAffectedTeachers = userRepo.registerTeacherUsers(course.getTeachers());
        logger.info("Registering TAs...");
        int rowsAffectedTAs = userRepo.registerTAUsers(course.getTas());
        logger.info("Registering Course...");
        int rowsAffectedCourse = courseRepo.insertCourse(course);
        return rowsAffectedStuds + rowsAffectedTeachers + rowsAffectedTAs + rowsAffectedCourse;
    }

    public Course getCourseByCourseCode(String courseCode) {
        return courseRepo.getCourseByCode(courseCode);
    }

    public List<SimpleCourseWithName> getActiveCourses(int id) {
        return courseRepo.getActiveOrInactiveCoursesByUserId(id, true);
    }

    public List<SimpleCourseWithName> getInactiveCourses(int id) {
        return courseRepo.getActiveOrInactiveCoursesByUserId(id, false);
    }
}
