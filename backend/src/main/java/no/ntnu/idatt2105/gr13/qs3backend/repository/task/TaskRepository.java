package no.ntnu.idatt2105.gr13.qs3backend.repository.task;

import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskWithId;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The interface for the Task repository.
 */
public interface TaskRepository {

    /**
     * Gets unfinished tasks for user from course with given hash id.
     *
     * @param courseHashId the course hash id
     * @param userId       the user id
     * @return the task from course hash id
     */
    List<TaskWithId> getTaskFromCourseHashId(String courseHashId, int userId);

    /**
     * Validate student tasks and closes queueInfo instance.
     *
     * @param queueInfoId the queue info id
     * @param tasks       the tasks
     * @return the int
     */
    @Transactional
    int validateStudentTasks(int queueInfoId, List<Task> tasks);
}
