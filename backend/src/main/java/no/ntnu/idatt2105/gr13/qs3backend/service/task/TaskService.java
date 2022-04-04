package no.ntnu.idatt2105.gr13.qs3backend.service.task;

import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskWithId;
import no.ntnu.idatt2105.gr13.qs3backend.repository.task.JdbcTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Task service.
 */
@Service
public class TaskService {

    @Autowired
    JdbcTaskRepository taskRepo;

    /**
     * Gets tasks related to a course.
     *
     * @param courseHashId the course hash id
     * @param userId       the user id
     * @return the task from course hash id
     */
    public List<TaskWithId> getTaskFromCourseHashId(String courseHashId, int userId) {
        return taskRepo.getTaskFromCourseHashId(courseHashId, userId);
    }

    /**
     * Validate tasks of a student in a queue.
     *
     * @param queueInfoId the queue info id
     * @param tasks       the tasks
     * @return the int
     */
    public int validateStudentTasks(int queueInfoId, List<Task> tasks) {
        return taskRepo.validateStudentTasks(queueInfoId, tasks);
    }
}
