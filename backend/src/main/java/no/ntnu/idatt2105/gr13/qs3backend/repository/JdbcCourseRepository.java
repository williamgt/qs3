package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.Course;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.Teacher;
import no.ntnu.idatt2105.gr13.qs3backend.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCourseRepository {
    Logger logger = LoggerFactory.getLogger(JdbcCourseRepository.class);

    String selectAllTeachersWithCodeIDATT1001 = "SELECT Person.firstname, Person.lastname FROM Course\n" +
            "INNER JOIN CoursePerson ON Course.courseCode = CoursePerson.courseCode AND Course.year= CoursePerson.year AND Course.term = CoursePerson.term\n" +
            "INNER JOIN Person ON CoursePerson.id = Person.id\n" +
            "INNER JOIN Teacher ON Person.id = Teacher.id\n" +
            "WHERE Course.courseCode='IDATT1001'";
    String getSelectAllTeachersWithCodeIDATT1001Mk2 = "SELECT Person.firstname, Person.lastname FROM Course\n" +
            "INNER JOIN CoursePerson ON Course.courseCode = CoursePerson.courseCode AND Course.year= CoursePerson.year AND Course.term = CoursePerson.term\n" +
            "INNER JOIN Person ON CoursePerson.personId = Person.id\n" +
            "INNER JOIN Teacher ON Person.id = Teacher.id\n" +
            "WHERE Course.courseCode='IDATT1001'";
    String selectAllTeachersWithGivenCode = "SELECT Person.firstname, Person.lastname " +
            "FROM Course INNER JOIN CoursePerson ON Course.courseCode = CoursePerson.courseCode AND Course.year = CoursePerson.year AND Course.term = CoursePerson.term " +
            "INNER JOIN Person ON CoursePerson.personId = Person.id " +
            "INNER JOIN Teacher ON Person.id = Teacher.id " +
            "WHERE Course.courseCode = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Course getCourseByCode(String courseCode) {
        try {
            Course course = jdbcTemplate.queryForObject("SELECT * FROM Course WHERE courseCode=?",
                    BeanPropertyRowMapper.newInstance(Course.class), courseCode);
//            List<Teacher> teachers = jdbcTemplate.query(
//                    "SELECT Person.firstname, Person.lastname " +
//                            "FROM Course INNER JOIN CoursePerson ON Course.courseCode = CoursePerson.courseCode AND Course.year = CoursePerson.year AND Course.term = CoursePerson.term " +
//                            "INNER JOIN Person ON CoursePerson.personId = Person.id " +
//                            "INNER JOIN Teacher ON Person.id = Teacher.id " +
//                            "WHERE Course.courseCode = ?",
//                    courseCode,
//                    (rs, rowNum) -> new Teacher(rs.getString("firstname"), rs.getString("lastname")));
            List<Teacher> teachers = jdbcTemplate.query(selectAllTeachersWithGivenCode)

            logger.info("Found course with code " + courseCode + ", returning...");
            return course;
        } catch (IncorrectResultSizeDataAccessException e) {
            logger.info("Course " + courseCode + " not found: " + e.getMessage());
            return null;
        }
    }
}
