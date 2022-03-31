package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.Course;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.Student;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.Teacher;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.TeacherAssistant;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Transactional
    public int insertCourse(Course course) {
        String insertIntoCourseString = "INSERT INTO Course (courseCode, year, term, courseName) VALUES (?,?,?,?)";

        String insertIntoTasksString = "INSERT INTO Tasks (amount, courseCode, year, term) VALUES (?,?,?,?)";
        String insertIntoSetOfTasksString = "INSERT INTO TaskSet (amountMustDone, taskId) VALUES (?,?)";
        String insertIntoTaskString = "INSERT INTO Task (description, taskSetId) VALUES (?,?)";
        String getTasksIdString = "SELECT tasksId FROM Tasks WHERE courseCode=? AND year=? AND term=?";
        String getTaskSetIdString = "SELECT TaskSetId FROM TaskSet WHERE tasksId=?";

        String insertIntoTeacherCourseString = "INSERT INTO TeacherCourse (teacherId, courseCode, year, term) VALUES (?,?,?,?)";
        String insertIntoTACourseString = "INSERT INTO TACourse (tAId, courseCode, year, term) VALUES (?,?,?,?)";
        String insertIntoStudentCourseString = "INSERT INTO StudentCourse (studentId, courseCode, year, term) VALUES (?,?,?,?)";
        String getPersonIdByEmailString = "SELECT personID FROM User WHERE email=?";


        int insertIntoCourseInt = 0;
        int insertIntoTasksInt = 0;
        int insertIntoSetOfTasksInt = 0;
        int insertIntoTaskInt = 0;
        int insertIntoTeacherCourseInt = 0;
        int insertIntoTACourseInt = 0;
        int insertIntoUserCourseInt = 0;

        //Insertions related to Course
        //Inserting info into Course table
        insertIntoCourseInt = jdbcTemplate.update(insertIntoCourseString,
                new Object[] {course.getCourseCode(), course.getYear(), course.getTerm(), course.getCourseName()});

        //Insertions related to Tasks
        //Inserting info into Tasks table
        insertIntoTasksInt = jdbcTemplate.update(insertIntoTasksString,
                new Object[] {course.getObligatoryTaskAmount(), course.getCourseCode(), course.getYear(), course.getTerm()});

        //Getting the id of newly inserted Tasks instance
        Integer tasksId = jdbcTemplate.queryForObject(getTasksIdString,
                new Object[] {course.getCourseCode(), course.getYear(), course.getTerm()}, Integer.class);
        //Looping through all the sets of tasks in given course and inserting these
        for(int i = 0; i < course.getSetOfTasks(); i++) {
            insertIntoSetOfTasksInt += jdbcTemplate.update(insertIntoSetOfTasksString,
                    new Object[] {course.getObligatoryPerSet()[i], tasksId});
        }

        //Getting the id of all new TaskSet instances
        List<Integer> taskSetIds = jdbcTemplate.query(getTaskSetIdString, new BeanPropertyRowMapper<>(Integer.class)); //TODO this might fail
        for(int i = 0; i < taskSetIds.size(); i++) {
            for(int j = 0; j < course.getTasksInEachSet().get(i).size(); j++) {
                insertIntoTaskInt += jdbcTemplate.update(insertIntoTaskString,
                        new Object[] {course.getTasksInEachSet().get(i).get(j).getDescription(), taskSetIds.get(i)});
            }
        }
        //Not sure if allowed:
        //String getTaskSetsString = "SELECT * FROM TaskSet WHERE tasksId=?";
        //List<TaskSet> taskSets = jdbcTemplate.query(getTaskSetsString, new BeanPropertyRowMapper<>(TaskSet.class));

        //TODO this part assumes everything in the person and user related tables are taken care of
        //Insertions related to persons
        Integer userId;
        for(Teacher t : course.getTeachers()){
            userId = jdbcTemplate.queryForObject(getPersonIdByEmailString, new Object[] {t.getEmail()}, Integer.class);
            insertIntoTeacherCourseInt += jdbcTemplate.update(insertIntoTeacherCourseString,
                    new Object[] {userId, course.getCourseCode(), course.getYear(), course.getTerm()});
        }

        for(TeacherAssistant ta : course.getTas()){
            userId = jdbcTemplate.queryForObject(getPersonIdByEmailString, new Object[] {ta.getEmail()}, Integer.class);
            insertIntoTACourseInt += jdbcTemplate.update(insertIntoTACourseString,
                    new Object[] {userId, course.getCourseCode(), course.getYear(), course.getTerm()});
        }

        for(Student s : course.getStudents()){
            userId = jdbcTemplate.queryForObject(getPersonIdByEmailString, new Object[] {s.getEmail()}, Integer.class);
            insertIntoUserCourseInt += jdbcTemplate.update(insertIntoStudentCourseString,
                    new Object[] {userId, course.getCourseCode(), course.getYear(), course.getTerm()});
        }

        logger.info("Inserted new course and related info without any exceptions.");
        return insertIntoCourseInt + insertIntoTasksInt + insertIntoSetOfTasksInt + insertIntoTaskInt + insertIntoTeacherCourseInt + insertIntoTACourseInt + insertIntoUserCourseInt;
    }

    private String updateCourseString = "UPDATE Course SET year=?, term=?, courseCode=?, courseName=? WHERE courseCode=";
    private String updateTasksString = "UPDATE Tasks SET amount=? WHERE courseCode=? AND year=? AND term=?";
    private String updateTaskSetString = "UPDATE TaskSet SET amountMustDone=? WHERE tasksID=?";
    private String updateTaskString = "UPDATE Task SET description=? WHERE ";
    private String getTasksId = "Select taskId FROM Tasks WHERE courseCode=? AND year=? AND term=?";
    private String getTaskSetIds = "Select taskSetId FROM TaskSet WHERE tasksId=?";
    @Transactional
    public int updateCourse(String courseCode, Course course) { //Returns the number of rows affected
        int updatedInCourse;
        int updatedTasks;
        int updatedTaskSet = 0;
        int updatedTask;

        int tasksId;
        //Updating Course, and if that goes well...
        updatedInCourse = jdbcTemplate.update(updateCourseString+courseCode,
                new Object[] {course.getYear(), course.getTerm(), course.getCourseCode(), course.getCourseName()});
        if (updatedInCourse > 0) {
            //Updating total obligatory tasks
            updatedTasks = jdbcTemplate.update(updateTasksString,
                    new  Object[] {course.getObligatoryTaskAmount(), course.getCourseCode(), course.getYear(), course.getTerm()});
            if (updatedTasks > 0) {
                //Need id of Tasks
                tasksId = (int) jdbcTemplate.queryForObject(getTasksId,
                        new Object[] {course.getCourseCode(), course.getYear(), course.getTerm()}, Integer.class);
                //Updating task sets...
                for(int i = 0; i < course.getSetOfTasks(); i++){ //TODO this will fail if more/less rows than originally!!!
                    updatedTaskSet += jdbcTemplate.update(updateTaskSetString,
                            new Object[] {course.getObligatoryPerSet()[i], tasksId}); //TODO this logic is straight up wrong, will produce not wanted behavior
                }
                if(updatedTaskSet > 0) {
                    //Need id of TaskSets
                    List<Map<String, Object>> taskSetIds = jdbcTemplate.queryForList(getTaskSetIds, tasksId);
                    //Updating each task in each set
                    for(int i = 0; i < taskSetIds.size(); i++) {
                        Integer taskSetId = (Integer) taskSetIds.get(i).get("taskSetId"); //Getting id of task set i

                        for(int j = 0; j < course.getTasksInEachSet().get(i).size(); j++) { //Looping through all tasks in task set i
                            //TODO need the ids of each task, or else this does not work
                        }
                    }

                }
            }
        }
        return 0;
    }
}
