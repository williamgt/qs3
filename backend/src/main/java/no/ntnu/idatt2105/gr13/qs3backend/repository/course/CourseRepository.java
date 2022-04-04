package no.ntnu.idatt2105.gr13.qs3backend.repository.course;

import no.ntnu.idatt2105.gr13.qs3backend.model.course.Course;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.CourseForm;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.SimpleCourseWithName;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The interface for the Course repository.
 */
public interface CourseRepository {
    /**
     * Gets course by code.
     *
     * @param courseCode the course code
     * @return the course by code
     */
    Course getCourseByCode(String courseCode);

    /**
     * Insert course int.
     *
     * @param course the course
     * @return the int
     */
    @Transactional
    int insertCourse(CourseForm course);

    /**
     * Update course int.
     *
     * @param courseCode the course code
     * @param course     the course
     * @return the int
     */
    @Transactional
    int updateCourse(String courseCode, Course course);

    /**
     * Gets active or inactive courses by user id.
     *
     * @param id     the id
     * @param active the active
     * @return the active or inactive courses by user id
     */
    List<SimpleCourseWithName> getActiveOrInactiveCoursesByUserId(int id, boolean active);
}
