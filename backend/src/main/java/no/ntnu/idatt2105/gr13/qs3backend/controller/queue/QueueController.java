package no.ntnu.idatt2105.gr13.qs3backend.controller.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.queue.Queue;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.QueueRequest;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.SimpleQueueWithCourseInfo;
import no.ntnu.idatt2105.gr13.qs3backend.service.queue.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param courseHashId hashed ID for course
     * @return queue
     */
    @RequestMapping("/course/{hash-id}") //localhost:8085/queue/course?course=code,year,term
    public Queue getQueueBySimpleCourse(@PathVariable("hash-id") String courseHashId) {
        logger.info("User requested queue from course with hash ID " +courseHashId + ".");
        Queue q = queueService.getQueueByCourse(courseHashId);
        if(q == null) {
            logger.info("No queue for course with hash ID" + courseHashId + " was found.");
        } else {
            logger.info("Returning queue for course with hash ID" + courseHashId);
        }
        return q;
    }

    /**
     * Method for activating / deactivating a queue
     * Returns OK if successful
     * Returns BAD_REQUEST if course hash is not in table
     * Returns NOT_MODIFIED if queue wasn't updated
     * @param courseHash
     * @return
     */
    @PutMapping("/activate-or-deactivate/{course-hash}")
    public ResponseEntity<String> activateOrDeactivateQueue(@PathVariable("course-hash") String courseHash) {
        logger.info("TA wants to activate or deactivate a queue.");
        int rowsAffected = queueService.activateOrDeactivateQueue(courseHash);
        if(rowsAffected == 1) {
            return new ResponseEntity<>("Queue was updated successfully.", HttpStatus.OK);
        } else if(rowsAffected == -1){
            return new ResponseEntity<>("Some information was not found for given course hash.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("No queue was updated.", HttpStatus.NOT_MODIFIED);
        }
    }

    /**
     * Returns list of active queues for given tAId.
     * Must at least have TA authority
     * @param tAId
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/ta-active-queue/{ta-id}")
    public List<SimpleQueueWithCourseInfo> taGetActiveQueues(@PathVariable("ta-id") String tAId) {
        logger.info("TA with user id " + tAId + " requested active queues.");
        List<SimpleQueueWithCourseInfo> qs = queueService.taGetActiveQueue(tAId);
        if(qs == null) {
            logger.info("No active queues for TA with id "+ tAId);
        } else{
            logger.info("Returning active queues for TA with id " + tAId);
        }
        return qs;
    }

    /**
     * Returns a list of inactive queues for given TaID
     * Must at least have TA authority
     * @param tAId
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/ta-inactive-queue/{ta-id}")
    public List<SimpleQueueWithCourseInfo> taGetInactiveQueues(@PathVariable("ta-id") String tAId) {
        logger.info("TA with user id " + tAId + " requested inactive queues.");
        List<SimpleQueueWithCourseInfo> qs = queueService.taGetInactiveQueue(tAId);
        if(qs == null) {
            logger.info("No inactive queues for TA with id "+ tAId);
        } else{
            logger.info("Returning inactive queues for TA with id " + tAId);
        }
        return qs;
    }

    //Method that was supposed to let the teaching assistant update the message related to a course's queue
    /*@PutMapping("/temp1")
    public int putTAMessage(TAMessageCourse courseAndMsg) {
        return 0;
    }*/


    /**
     * Checks if student with given id is in a queue. If student is, they shouldn't be able to queue up again
     * or in another queue.
     * @param hashId
     * @param studentId
     * @return
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("register-status/{hashId}/{studentId}")
    public boolean isInQueue(@PathVariable("hashId") String hashId, @PathVariable("studentId") int studentId) {
        logger.info("Checking if student with id " + studentId + " is in queue or not for course with hash " + hashId);
        boolean inQueue = queueService.studentIsInQueue(hashId, studentId);
        if(inQueue) {
            logger.info("Student in queue, can't queue up now.");
        } else {
            logger.info("Student not in queue, can queue up now.");
        }
        return inQueue;
    }

    /**
     * Method for student to queue up in a specific course
     * If already in queue returns NOT_MODIFIED
     * If successfully in queue return CREATED
     * If none of the above INTERNAL_SERVER_ERROR
     * @param req
     * @return
     */
    @PostMapping("/queue-up")
    public ResponseEntity<String> queueUp(@RequestBody QueueRequest req) {
        logger.info("Received queue request from user");
        try{
            int rowsAffected = queueService.queueUp(req);
            if(rowsAffected == -1) {
                logger.info("Already in queue, can't queue up again.");
                return new ResponseEntity<>("Successfully got into queue.", HttpStatus.NOT_MODIFIED);
            }
            logger.info("Queued up and affected rows " + rowsAffected);
            return new ResponseEntity<>("Successfully got into queue.", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.info("An exception was thrown when queueing up: " + e.getMessage());
            return new ResponseEntity<>("Could not register queue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
