package no.ntnu.idatt2105.gr13.qs3backend.service.task;

import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskWithId;
import no.ntnu.idatt2105.gr13.qs3backend.repository.task.JdbcTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    JdbcTaskRepository taskRepo;

    public List<TaskWithId> getTaskFromCourseHashId(String courseHashId, int userId) {
        return taskRepo.getTaskFromCourseHashId(courseHashId, userId);
    }

    public int validateStudentTasks(int queueInfoId, List<Task> tasks) {
        return taskRepo.validateStudentTasks(queueInfoId, tasks);
    }
}
