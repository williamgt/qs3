package no.ntnu.idatt2105.gr13.qs3backend.repository.task;

import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskWithId;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskRepository {
    List<TaskWithId> getTaskFromCourseHashId(String courseHashId, int userId);

    @Transactional
    int validateStudentTasks(int queueInfoId, List<Task> tasks);
}
