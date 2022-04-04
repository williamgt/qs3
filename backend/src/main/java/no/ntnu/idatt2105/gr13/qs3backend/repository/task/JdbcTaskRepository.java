package no.ntnu.idatt2105.gr13.qs3backend.repository.task;

import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskWithId;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.ValidatedTasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JdbcTaskRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(JdbcTaskRepository.class);

    public List<TaskWithId> getTaskFromCourseHashId(String courseHashId, int userId) {
        String selectAllTasksNotFinishedQuery = "SELECT Task.taskId, Task.description FROM Task " +
                "INNER JOIN TaskSet ON Task.taskSetId=TaskSet.taskSetId " +
                "INNER JOIN Tasks ON TaskSet.tasksId=Tasks.tasksId " +
                "INNER JOIN Course ON Tasks.courseCode=Course.courseCode AND Tasks.year=Course.year AND Tasks.term=Course.term " +
                "INNER JOIN StudentCourse ON Course.courseCode=StudentCourse.courseCode AND StudentCourse.year=Course.year AND StudentCourse.term=Course.term " +
                "INNER JOIN Queue ON Course.courseCode=Queue.courseCode AND Course.year=Queue.year AND Course.term=Queue.term " +
                "INNER JOIN QueueInfo ON Queue.queueId=QueueInfo.queueId " +
                "INNER JOIN TaskQueueInfo ON TaskQueueInfo.taskId=Task.taskId AND TaskQueueInfo.queueInfoId=QueueInfo.queueInfoId " +
                "WHERE StudentCourse.studentId=? AND Course.hashId=? AND TaskQueueInfo.validated=0"; //Get the tasks a student ahs queued up for, only needs to specify queueInfoId

        selectAllTasksNotFinishedQuery = "SELECT DISTINCT Task.taskId, Task.description FROM Task " +
                "INNER JOIN TaskSet ON Task.taskSetId=TaskSet.taskSetId " +
                "INNER JOIN Tasks ON TaskSet.tasksId=Tasks.tasksId " +
                "INNER JOIN Course ON Tasks.courseCode=Course.courseCode AND Tasks.year=Course.year AND Tasks.term=Course.term " +
                "INNER JOIN StudentCourse ON Course.courseCode=StudentCourse.courseCode AND StudentCourse.year=Course.year AND StudentCourse.term=Course.term " +
                "INNER JOIN Queue ON Course.courseCode=Queue.courseCode AND Course.year=Queue.year AND Course.term=Queue.term " +
                "INNER JOIN QueueInfo ON Queue.queueId=QueueInfo.queueId " +
                "WHERE StudentCourse.studentId=? AND Course.hashId=?"; //Gets every task from a course

        selectAllTasksNotFinishedQuery = "SELECT DISTINCT Task.taskId, Task.description FROM Task" +
                "                INNER JOIN TaskSet ON Task.taskSetId=TaskSet.taskSetId" +
                "                INNER JOIN Tasks ON TaskSet.tasksId=Tasks.tasksId" +
                "                INNER JOIN Course ON Tasks.courseCode=Course.courseCode AND Tasks.year=Course.year AND Tasks.term=Course.term" +
                "                INNER JOIN StudentCourse ON Course.courseCode=StudentCourse.courseCode AND StudentCourse.year=Course.year AND StudentCourse.term=Course.term" +
                "                INNER JOIN Queue ON Course.courseCode=Queue.courseCode AND Course.year=Queue.year AND Course.term=Queue.term" +
                "                WHERE Course.hashId=?"; //Selects every task from given course

        selectAllTasksNotFinishedQuery = "SELECT DISTINCT Task.taskId, Task.description FROM Task " +
                "INNER JOIN TaskSet ON Task.taskSetId=TaskSet.taskSetId " +
                "INNER JOIN Tasks ON TaskSet.tasksId=Tasks.tasksId " +
                "INNER JOIN Course ON Tasks.courseCode=Course.courseCode AND Tasks.year=Course.year AND Tasks.term=Course.term " +
                "INNER JOIN StudentCourse ON Course.courseCode=StudentCourse.courseCode AND StudentCourse.year=Course.year AND StudentCourse.term=Course.term " +
                "INNER JOIN Queue ON Course.courseCode=Queue.courseCode AND Course.year=Queue.year AND Course.term=Queue.term " +
                "INNER JOIN QueueInfo ON Queue.queueId=QueueInfo.queueId " +
                "INNER JOIN TaskQueueInfo ON TaskQueueInfo.queueInfoId=QueueInfo.queueInfoId " +
                "WHERE StudentCourse.studentId=? AND Course.hashId=? AND NOT TaskQueueInfo.validated=1"; //Gets every task that is not done for student in a course

        String selectAllTasksFinishedQuery = "SELECT DISTINCT Task.taskId, TaskQueueInfo.validated, Task.description FROM Task" +
                "INNER JOIN TaskSet ON Task.taskSetId=TaskSet.taskSetId" +
                "INNER JOIN Tasks ON TaskSet.tasksId=Tasks.tasksId" +
                "INNER JOIN Course ON Tasks.courseCode=Course.courseCode AND Tasks.year=Course.year AND Tasks.term=Course.term" +
                "INNER JOIN StudentCourse ON Course.courseCode=StudentCourse.courseCode AND StudentCourse.year=Course.year AND StudentCourse.term=Course.term" +
                "INNER JOIN Queue ON Course.courseCode=Queue.courseCode AND Course.year=Queue.year AND Course.term=Queue.term" +
                "INNER JOIN QueueInfo ON Queue.queueId=QueueInfo.queueId" +
                "INNER JOIN TaskQueueInfo ON TaskQueueInfo.queueInfoId=QueueInfo.queueInfoId AND Task.taskId=TaskQueueInfo.taskId" +
                "WHERE StudentCourse.studentId=? AND Course.hashId=? AND TaskQueueInfo.validated=1"; //Gets the done tasks

        selectAllTasksNotFinishedQuery = "SELECT DISTINCT Task.taskId, Task.description FROM Task" +
                "INNER JOIN TaskSet ON Task.taskSetId=TaskSet.taskSetId" +
                "INNER JOIN Tasks ON TaskSet.tasksId=Tasks.tasksId" +
                "INNER JOIN Course ON Tasks.courseCode=Course.courseCode AND Tasks.year=Course.year AND Tasks.term=Course.term" +
                "INNER JOIN StudentCourse ON Course.courseCode=StudentCourse.courseCode AND StudentCourse.year=Course.year AND StudentCourse.term=Course.term" +
                "INNER JOIN Queue ON Course.courseCode=Queue.courseCode AND Course.year=Queue.year AND Course.term=Queue.term" +
                "INNER JOIN QueueInfo ON Queue.queueId=QueueInfo.queueId" +
                "INNER JOIN TaskQueueInfo ON TaskQueueInfo.queueInfoId=QueueInfo.queueInfoId" +
                "WHERE StudentCourse.studentId=? AND Course.hashId=? AND validated=0 AND Task.taskId NOT IN (" +
                "    SELECT DISTINCT Task.taskId FROM Task" +
                "    INNER JOIN TaskSet ON Task.taskSetId=TaskSet.taskSetId" +
                "    INNER JOIN Tasks ON TaskSet.tasksId=Tasks.tasksId" +
                "    INNER JOIN Course ON Tasks.courseCode=Course.courseCode AND Tasks.year=Course.year AND Tasks.term=Course.term" +
                "    INNER JOIN StudentCourse ON Course.courseCode=StudentCourse.courseCode AND StudentCourse.year=Course.year AND StudentCourse.term=Course.term" +
                "    INNER JOIN Queue ON Course.courseCode=Queue.courseCode AND Course.year=Queue.year AND Course.term=Queue.term" +
                "    INNER JOIN QueueInfo ON Queue.queueId=QueueInfo.queueId" +
                "    INNER JOIN TaskQueueInfo ON TaskQueueInfo.queueInfoId=QueueInfo.queueInfoId AND Task.taskId=TaskQueueInfo.taskId" +
                "    WHERE StudentCourse.studentId=? AND Course.hashId=? AND TaskQueueInfo.validated=1)"; //Seems to do the trick

        List<TaskWithId> tasks = jdbcTemplate.query(selectAllTasksNotFinishedQuery, (rs, rowNum) ->
            new TaskWithId(
                    rs.getString("Task.description"),
                    rs.getInt("Task.taskId")
            )
        , new Object[]{userId, courseHashId, userId, courseHashId});

        logger.info("User with id " + userId + " has " + tasks.size() + " unvalidated tasks in course with hash " + courseHashId + ".");

        return tasks;
    }

    @Transactional
    public int validateStudentTasks(int queueInfoId, ValidatedTasks tasks) {
        String selectAllTasksFromGivenQueueInfoIdQuery = "SELECT Task.taskId, Task.description FROM Task " +
                "INNER JOIN TaskQueueInfo ON Task.taskId=TaskQueueInfo.taskId " +
                "WHERE TaskQueueInfo.queueInfoId=?";

        String updateToValidatedQuery = "UPDATE TaskQueueInfo SET validated=1 WHERE taskId=? AND queueInfoId=?";

        String closeQueueInfoInstanceQuery = "UPDATE QueueInfo SET active=0 WHERE queueInfoId=?";

        int rowsAffected = 0;

        //Getting the tasks the student was registered in the queue with
        List<TaskWithId> tasksFromDb = jdbcTemplate.query(selectAllTasksFromGivenQueueInfoIdQuery,
                BeanPropertyRowMapper.newInstance(TaskWithId.class), queueInfoId);

        //Checking if the tasks in the ValidatedTasks are the same as the ones gotten from DB, if so mark as validated
        for(int i = 0; i < tasks.getTasks().size(); i++){
            for(TaskWithId t : tasksFromDb){
                if(tasks.getTasks().get(i).getDescription().equals(t.getDescription())) {
                    rowsAffected += jdbcTemplate.update(updateToValidatedQuery, t.getTaskId(), queueInfoId);
                }
            }
        }

        //Closing QueueInfo instance
        rowsAffected += jdbcTemplate.update(closeQueueInfoInstanceQuery, queueInfoId);

        return rowsAffected;
    }
}
