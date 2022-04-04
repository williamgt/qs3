package no.ntnu.idatt2105.gr13.qs3backend.repository.course;

import no.ntnu.idatt2105.gr13.qs3backend.model.course.Course;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.CourseForm;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.SimpleCourseWithName;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseRepository {
    Course getCourseByCode(String courseCode);

    @Transactional
    int insertCourse(CourseForm course);

    @Transactional
    int updateCourse(String courseCode, Course course);

    List<SimpleCourseWithName> getActiveOrInactiveCoursesByUserId(int id, boolean active);
}
