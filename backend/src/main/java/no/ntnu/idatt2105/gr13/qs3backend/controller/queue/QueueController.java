package no.ntnu.idatt2105.gr13.qs3backend.controller.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.queue.Queue;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.SimpleCourse;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.TAMessageCourse;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.QueueRequest;
import no.ntnu.idatt2105.gr13.qs3backend.service.queue.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/queue")
//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin("*")
public class QueueController {
    Logger logger = LoggerFactory.getLogger(QueueController.class);

    @Autowired
    private QueueService queueService;

    /**
     * Returns all information regarding a queue related to a given course.
     * @param courseHashId hashed ID of course
     * @return queue
     */
    @RequestMapping("/course/{hashId}") //localhost:8085/queue/course?course=code,year,term
    public Queue getQueueBySimpleCourse(@PathVariable("hashId") String courseHashId) {
        logger.info("User requested queue from course with hash ID " +courseHashId + ".");
        Queue q = queueService.getQueueByCourse(courseHashId);
        if(q == null) {
            logger.info("No queue for course with hash ID" + courseHashId + " was found.");
        } else {
            logger.info("Returning queue for course with hash ID" + courseHashId);
        }
        return q;
    }

    @PutMapping("/temp2")
    public int activateOrDeactivateQueue(SimpleCourse course) {
        return 0;
    }

    @PutMapping("/temp1")
    public int putTAMessage(TAMessageCourse courseAndMsg) {
        return 0;
    }

    @PostMapping("/queue-up")
    public ResponseEntity<String> queueUp(@RequestBody QueueRequest req) {
        logger.info("Received queue request from user");
        try{
            int rowsAffected = queueService.queueUp(req);
            logger.info("Queued up and affected rows " + rowsAffected);
            return new ResponseEntity<>("Successfully got into queue.", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.info("An exception was thrown when queueing up: " + e.getMessage());
            return new ResponseEntity<>("Could not register queue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
