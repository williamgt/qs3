package no.ntnu.idatt2105.gr13.qs3backend.controller.task;

import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskWithId;
import no.ntnu.idatt2105.gr13.qs3backend.service.task.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
@CrossOrigin("*")
public class TaskController {
    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    TaskService taskService;

    @GetMapping("/{hashId}/{userId}")
    public List<TaskWithId> getTaskFromCourseHashId(@PathVariable String hashId, @PathVariable int userId) {
        logger.info("User with ID " + userId + " requested unvalidated tasks from course with hash " + hashId + ".");
        return taskService.getTaskFromCourseHashId(hashId, userId);
    }
}
