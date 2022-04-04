package no.ntnu.idatt2105.gr13.qs3backend.controller.task;

import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskWithId;
import no.ntnu.idatt2105.gr13.qs3backend.service.task.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/validate/{queue-info-id}")
    public ResponseEntity<String> validateStudentTasks(@PathVariable("queue-info-id") int queueInfoId, @RequestBody List<Task> tasks) {
        logger.info("TA validates student wit queueInfoId " + queueInfoId);
        int rowsAffected = taskService.validateStudentTasks(queueInfoId, tasks);
        if(rowsAffected == 1) {
            logger.info("No tasks were validated, but queue instance with queueInfoId "+queueInfoId+" was closed.");
            return new ResponseEntity<>("No tasks were validated, but queue instance was closed.", HttpStatus.OK);
        } else if (rowsAffected > 1) {
            logger.info((rowsAffected - 1) + " tasks were validated, and queue instance with queueInfoId "+queueInfoId+" was closed.");
            return new ResponseEntity<>((rowsAffected - 1) + " tasks were validated, and queue instance was closed.", HttpStatus.OK);
        } else {
            logger.info("Something went wrong when validating queueInfoIde " + queueInfoId);
            return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
