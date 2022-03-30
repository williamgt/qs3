package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.Course;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.Student;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.Teacher;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.TeacherAssistant;
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

    private String selectAllTeachersWithGivenCode = "SELECT Person.firstname, Person.lastname " +
            "FROM Course INNER JOIN CoursePerson ON Course.courseCode = CoursePerson.courseCode AND Course.year = CoursePerson.year AND Course.term = CoursePerson.term " +
            "INNER JOIN Person ON CoursePerson.personId = Person.id " +
            "INNER JOIN Teacher ON Person.id = Teacher.id " +
            "WHERE Course.courseCode = ?";

    private String selectAllTAsWithGivenCode = "SELECT Person.firstname, Person.lastname " +
            "FROM Course INNER JOIN CoursePerson ON Course.courseCode = CoursePerson.courseCode AND Course.year = CoursePerson.year AND Course.term = CoursePerson.term " +
            "INNER JOIN Person ON CoursePerson.personId = Person.id " +
            "INNER JOIN TA ON Person.id = TA.id " +
            "WHERE Course.courseCode = ?";

    private String selectAllStudentsWithGivenCode = "SELECT Person.firstname, Person.lastname " +
            "FROM Course INNER JOIN CoursePerson ON Course.courseCode = CoursePerson.courseCode AND Course.year = CoursePerson.year AND Course.term = CoursePerson.term " +
            "INNER JOIN Person ON CoursePerson.personId = Person.id " +
            "INNER JOIN Student ON Person.id = Student.id " +
            "WHERE Course.courseCode = ?";

    private String selectQueueForACourse ="SELECT *" +
            "FROM Queue WHERE courseCode = ? AND year = ? AND term =?";

    //@PreAuthoriz("hasAuthority()")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Course getCourseByCode(String courseCode) {
        try {
            Course course = jdbcTemplate.queryForObject("SELECT * FROM Course WHERE courseCode=?",
                    BeanPropertyRowMapper.newInstance(Course.class), courseCode);
            List<Teacher> teachers = jdbcTemplate.query(selectAllTeachersWithGivenCode, BeanPropertyRowMapper.newInstance(Teacher.class), courseCode);
            List<TeacherAssistant> tas = jdbcTemplate.query(selectAllTAsWithGivenCode, BeanPropertyRowMapper.newInstance(TeacherAssistant.class), courseCode);
            List<Student> students = jdbcTemplate.query(selectAllStudentsWithGivenCode, BeanPropertyRowMapper.newInstance(Student.class), courseCode);

            course.setTeachers(teachers);
            course.setTas(tas);
            course.setStudents(students);
            logger.info("Found course with code " + courseCode + ", returning...");
            return course;
        } catch (IncorrectResultSizeDataAccessException e) {
            logger.info("Course " + courseCode + " not found: " + e.getMessage());
            return null;
        }
    }

    private String updateCourseString = "UPDATE Course SET year=?, term=?, courseCode=?, courseName=? WHERE courseCode=";
    private String updateTasksString = "UPDATE Tasks SET amount=? WHERE courseCode=? AND year=? AND term=?";
    public int updateCourse(String courseCode, Course course) { //Returns the number of rows affected
        int updatedInCourse;
        int updatedTasks;
        int updatedTaskSet;
        int updatedTask;
        //Updating Course, and if that goes well...
        updatedInCourse = jdbcTemplate.update(updateCourseString+courseCode,
                new Object[] {course.getYear(), course.getTerm(), course.getCourseCode(), course.getCourseName()});
        if (updatedInCourse > 0) {
            //Updating tasks in each set and obligatory per set
            updatedTasks = jdbcTemplate.update(updateTasksString,
                    new  Object[] {course.getObligatoryTaskAmount(), course.getCourseCode(), course.getYear(), course.getTerm()});
            if (updatedTasks > 0) {
                //Updating task sets...
            }
        }
        return 0;
    }
}
