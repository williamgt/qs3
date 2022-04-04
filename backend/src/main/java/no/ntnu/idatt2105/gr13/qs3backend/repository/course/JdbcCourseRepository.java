package no.ntnu.idatt2105.gr13.qs3backend.repository.course;

import no.ntnu.idatt2105.gr13.qs3backend.model.course.Course;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.CourseForm;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.SimpleCourseWithName;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskList;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskSet;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.StudentUser;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.TAUser;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.TeacherUser;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.StudentUserBasic;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.TAUserBasic;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.TeacherUserBasic;
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

/**
 * Repository used for communicating with the DB regarding courses
 */
@Repository
public class JdbcCourseRepository implements CourseRepository{
    Logger logger = LoggerFactory.getLogger(JdbcCourseRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Gets a course and related information such as students, teaching assistants and teachers in the course,
     * with a given course code. Notable weakness: will fail if more courses with the same course code
     * is registered in the database. For a better implementation, future iterations could fix this by either adding
     * year and term or only use the course's hashed ID for the query.
     * @param courseCode the course code
     * @return Course with all it's related information
     */
    @Override
    public Course getCourseByCode(String courseCode) {
        try {
            logger.info("Getting course...");
            Course course = jdbcTemplate.queryForObject("SELECT * FROM Course WHERE courseCode=?",
                    BeanPropertyRowMapper.newInstance(Course.class), courseCode);

            String selectAllTeachersWithGivenCode = "SELECT User.firstname, User.lastname " +
                    "FROM Course INNER JOIN TeacherCourse ON Course.courseCode = TeacherCourse.courseCode AND Course.year = TeacherCourse.year AND Course.term = TeacherCourse.term " +
                    "INNER JOIN User ON TeacherCourse.teacherId = User.id " +
                    "WHERE Course.courseCode = ?";

            String selectAllTAsWithGivenCode = "SELECT User.firstname, User.lastname " +
                    "FROM Course INNER JOIN TACourse ON Course.courseCode = TACourse.courseCode AND Course.year = TACourse.year AND Course.term = TACourse.term " +
                    "INNER JOIN User ON TACourse.tAId = User.id " +
                    "WHERE Course.courseCode = ?";

            String selectAllStudentsWithGivenCode = "SELECT User.firstname, User.lastname " +
                    "FROM Course INNER JOIN StudentCourse ON Course.courseCode = StudentCourse.courseCode AND Course.year = StudentCourse.year AND Course.term = StudentCourse.term " +
                    "INNER JOIN User ON StudentCourse.studentId = User.id " +
                    "WHERE Course.courseCode = ?";

            logger.info("Getting teachers...");
            List<TeacherUser> teachers = jdbcTemplate.query(selectAllTeachersWithGivenCode, BeanPropertyRowMapper.newInstance(TeacherUser.class), courseCode);
            logger.info("Getting teaching assistants...");
            List<TAUser> tas = jdbcTemplate.query(selectAllTAsWithGivenCode, BeanPropertyRowMapper.newInstance(TAUser.class), courseCode);
            logger.info("Getting students...");
            List<StudentUser> students = jdbcTemplate.query(selectAllStudentsWithGivenCode, BeanPropertyRowMapper.newInstance(StudentUser.class), courseCode);

            course.setTeachers(teachers);
            course.setTas(tas);
            course.setStudents(students);

            logger.info("Getting tasks...");
            String selectTasksGivenBasicCourseInfo = "SELECT * FROM Tasks WHERE courseCode=? AND year=? AND term=?";
            TaskList tasksList = jdbcTemplate.queryForObject(selectTasksGivenBasicCourseInfo,
                    BeanPropertyRowMapper.newInstance(TaskList.class), course.getCourseCode(), course.getYear(), course.getTerm());

            logger.info("Getting task sets...");
            String selectTaskSetsRelatedToTasks = "SELECT TaskSet.amountMustDone, TaskSet.taskSetId FROM TaskSet INNER JOIN Tasks ON TaskSet.tasksId=Tasks.tasksId WHERE Tasks.courseCode=? AND Tasks.year=? AND Tasks.term=?";
            List<TaskSet> taskSets = jdbcTemplate.query(selectTaskSetsRelatedToTasks, BeanPropertyRowMapper.newInstance(TaskSet.class), course.getCourseCode(), course.getYear(), course.getTerm());

            course.setSetOfTasks(taskSets.size()); //Setting the right values for
            course.setObligatoryPerSet(new int[course.getSetOfTasks()]);
            for(int i = 0; i < course.getSetOfTasks(); i++){
                course.getObligatoryPerSet()[i] = taskSets.get(i).getAmountMustDone();
            }
            /*String selectRelatedTasks = "SELECT Task.description, Task.taskSetId FROM " +
                    "Task INNER JOIN TaskSet ON Task.taskSetId=TaskSet.taskSetId" +
                    "TaskSet INNER JOIN Tasks ON TaskSet.tasksId=Tasks.tasksId WHERE Tasks.courseCode=? AND Tasks.year=? AND Tasks.term=?";
            List<Task> tasks =  jdbcTemplate.query(selectRelatedTasks, BeanPropertyRowMapper.newInstance(Task.class), course.getCourseCode(), course.getYear(), course.getTerm());*/

            String selectTasksRelatedToSet = "SELECT Task.description, Task.taskSetId FROM " +
                    "Task INNER JOIN TaskSet ON Task.taskSetId=TaskSet.taskSetId " +
                    "TaskSet INNER JOIN Tasks ON TaskSet.tasksId=Tasks.tasksId WHERE Tasks.courseCode=? AND Tasks.year=? AND Tasks.term=? AND TaskSet.taskSetId=?";
            int obligatoryTaskAmount = 0;
            List tasksInEach = null; //TODO might throw null pointer exception
            for(TaskSet t : taskSets) {
                logger.info("Getting a list of tasks...");
                List<Task> tasks =  jdbcTemplate.query(selectTasksRelatedToSet, BeanPropertyRowMapper.newInstance(Task.class), course.getCourseCode(), course.getYear(), course.getTerm(), t.getTaskSetId());
                //t.setTasks(tasks);
                tasksInEach.add(tasks);
                obligatoryTaskAmount += tasks.size(); //Counting all obligatory tasks
            }

            course.setObligatoryTaskAmount(obligatoryTaskAmount);
            course.setTasksInEachSet(tasksInEach);


            logger.info("Found course with code " + courseCode + ", returning...");
            return course;
        } catch (IncorrectResultSizeDataAccessException e) {
            logger.info("Course " + courseCode + " not found: " + e.getMessage());
            return null;
        }
    }

    /**
     * Registers a course in the DB based on information given in the form sent in. The insertion of rows into the DB
     * is transactional, and will rollback if an exception is thrown. An exception is thrown if one tries to register an
     * already registered course. Users are NOT added to the db here, the given users are assumed put in the DB already.
     * @param course the course form
     * @return the number of rows affected
     */
    @Override
    @Transactional
    public int insertCourse(CourseForm course) {
        String insertIntoCourseString = "INSERT INTO Course (courseCode, year, term, courseName) VALUES (?,?,?,?)";

        String insertIntoQueueString = "INSERT INTO Queue (courseCode, year, term) VALUES (?,?,?)";

        String insertIntoTasksString = "INSERT INTO Tasks (amount, courseCode, year, term) VALUES (?,?,?,?)";
        String insertIntoSetOfTasksString = "INSERT INTO TaskSet (amountMustDone, tasksId) VALUES (?,?)";
        String insertIntoTaskString = "INSERT INTO Task (description, taskSetId) VALUES (?,?)";
        String getTasksIdString = "SELECT tasksId FROM Tasks WHERE courseCode=? AND year=? AND term=?";
        String getTaskSetIdString = "SELECT taskSetId FROM TaskSet WHERE tasksId=?";

        String insertIntoTeacherCourseString = "INSERT INTO TeacherCourse (teacherId, courseCode, year, term) VALUES (?,?,?,?)";
        String insertIntoTACourseString = "INSERT INTO TACourse (tAId, courseCode, year, term) VALUES (?,?,?,?)";
        String insertIntoStudentCourseString = "INSERT INTO StudentCourse (studentId, courseCode, year, term) VALUES (?,?,?,?)";
        String getUserIdByEmail = "SELECT id FROM User WHERE email=?";


        int insertIntoCourseInt = 0;
        int insertIntoQueueInt = 0;
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

        //Insertion related to Queue
        insertIntoQueueInt = jdbcTemplate.update(insertIntoQueueString,
                new Object[] {course.getCourseCode(), course.getYear(), course.getTerm()});

        //Insertions related to Tasks
        //Inserting info into Tasks table
        insertIntoTasksInt = jdbcTemplate.update(insertIntoTasksString,
                new Object[] {course.getObligatoryTaskAmount(), course.getCourseCode(), course.getYear(), course.getTerm()});

        //Getting the id of newly inserted Tasks instance
        Integer tasksId = jdbcTemplate.queryForObject(getTasksIdString, Integer.class,
                new Object[] {course.getCourseCode(), course.getYear(), course.getTerm()});
        //Looping through all the sets of tasks in given course and inserting these
        for(int i = 0; i < course.getSetOfTasks(); i++) {
            insertIntoSetOfTasksInt += jdbcTemplate.update(insertIntoSetOfTasksString,
                    new Object[] {course.getObligatoryPerSet().get(i), tasksId});
        }

        //Getting the id of all new TaskSet instances
        List<Integer> taskSetIds = jdbcTemplate.query(getTaskSetIdString, (rs, rowNum) ->
                Integer.valueOf(
                        rs.getInt("taskSetId")
                ), tasksId);

        for(int i = 0; i < taskSetIds.size(); i++) {
            for(int j = 0; j < course.getTasksInEachSet().get(i).size(); j++) {
                insertIntoTaskInt += jdbcTemplate.update(insertIntoTaskString,
                        new Object[] {course.getTasksInEachSet().get(i).get(j).getTask(), taskSetIds.get(i)});
            }
        }

        //TODO this part assumes everything in the person and user related tables are taken care of
        //Insertions related to Users NB into UserTypeCourse tables
        Integer id;
        for(TeacherUserBasic t : course.getTeachers()){
            id = jdbcTemplate.queryForObject(getUserIdByEmail, Integer.class, new Object[] {t.getEmail()});
            insertIntoTeacherCourseInt += jdbcTemplate.update(insertIntoTeacherCourseString,
                    new Object[] {id, course.getCourseCode(), course.getYear(), course.getTerm()});
        }

        for(TAUserBasic ta : course.getTas()){
            id = jdbcTemplate.queryForObject(getUserIdByEmail, Integer.class, new Object[] {ta.getEmail()});
            insertIntoTACourseInt += jdbcTemplate.update(insertIntoTACourseString,
                    new Object[] {id, course.getCourseCode(), course.getYear(), course.getTerm()});
        }

        for(StudentUserBasic s : course.getStudents()){
            id = jdbcTemplate.queryForObject(getUserIdByEmail, Integer.class, new Object[] {s.getEmail()});
            insertIntoUserCourseInt += jdbcTemplate.update(insertIntoStudentCourseString,
                    new Object[] {id, course.getCourseCode(), course.getYear(), course.getTerm()});
        }

        int totalRowsAffected = insertIntoCourseInt + insertIntoQueueInt + insertIntoTasksInt + insertIntoSetOfTasksInt + insertIntoTaskInt + insertIntoTeacherCourseInt + insertIntoTACourseInt + insertIntoUserCourseInt;
        logger.info("Inserted new course and related info without any exceptions. Rows affected: " + totalRowsAffected);
        return totalRowsAffected;
    }

    /**
     * Unfinished and broken implementation of a way to update a course. Is transactional since there are many rows that has
     * to be updated, and will rollback if an exception is thrown.
     * @param courseCode the course code
     * @param course     the course
     * @return
     */
    @Override
    @Transactional
    public int updateCourse(String courseCode, Course course) { //Returns the number of rows affected
        String updateCourseString = "UPDATE Course SET year=?, term=?, courseCode=?, courseName=? WHERE courseCode=";
        String updateTasksString = "UPDATE Tasks SET amount=? WHERE courseCode=? AND year=? AND term=?";
        String updateTaskSetString = "UPDATE TaskSet SET amountMustDone=? WHERE tasksID=?";
        String updateTaskString = "UPDATE Task SET description=? WHERE ";
        String getTasksId = "Select tasksId FROM Tasks WHERE courseCode=? AND year=? AND term=?";
        String getTaskSetIds = "Select taskSetId FROM TaskSet WHERE tasksId=?";

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

    /**
     * Gets all inactive or active courses that a student is registered in.
     * @param id     the id of the student in question
     * @param active whether one wants the active or inactive courses
     * @return
     */
    @Override
    public List<SimpleCourseWithName> getActiveOrInactiveCoursesByUserId(int id, boolean active) {
        int activeInt = active ? 1 : 0;

        String coursesQuery = "SELECT Course.courseCode, Course.year, Course.term, Course.courseName, Course.hashId FROM Course " +
                "INNER JOIN Queue ON Course.courseCode=Queue.courseCode AND Course.year=Queue.year AND Course.term=Queue.term " +
                "INNER JOIN StudentCourse ON Course.courseCode=StudentCourse.courseCode AND Course.year=StudentCourse.year AND Course.term=StudentCourse.term " +
                "WHERE Queue.active=? AND StudentCourse.studentId=? ";

        List<SimpleCourseWithName> courses = jdbcTemplate.query(coursesQuery,
                BeanPropertyRowMapper.newInstance(SimpleCourseWithName.class), activeInt, id);

        return courses;
    }
}
