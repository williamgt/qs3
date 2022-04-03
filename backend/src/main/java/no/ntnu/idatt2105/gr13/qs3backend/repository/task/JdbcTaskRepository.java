package no.ntnu.idatt2105.gr13.qs3backend.repository.task;

import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskWithId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        , new Object[]{userId, courseHashId});

        logger.info("User with id " + userId + " has " + tasks.size() + " unvalidated tasks in course with hash " + courseHashId + ".");

        return tasks;
    }
}
