package no.ntnu.idatt2105.gr13.qs3backend.controller.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.course.Course;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.Queue;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.SimpleCourse;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.TAMessageCourse;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.QueueRequest;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.SimpleQueue;
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
     * @param course course object containing information that only one could have
     * @return queue
     */
    @RequestMapping("/course") //localhost:8085/queue/course?course=code,year,term
    public Queue getQueueBySimpleCourse(@RequestParam List<String> course) {
        if(course.size() != 3) {
            logger.info("Invalid amount of arguments when getting course.");
            return null;
        }
        SimpleCourse userCourse = new SimpleCourse(course.get(0),course.get(1),course.get(2));
        logger.info("User requested queue from course " +userCourse.getCourseCode() + ", " + userCourse.getYear()+ ", "+userCourse.getTerm());
        Queue q = queueService.getQueueByCourse(userCourse);
        if(q == null) {
            logger.info("No queue for course " +userCourse.getCourseCode() + ", " + userCourse.getYear()+ ", "+userCourse.getTerm() + " was found");
        } else {
            logger.info("Returning queue for course " +userCourse.getCourseCode() + ", " + userCourse.getYear()+ ", "+userCourse.getTerm());
        }
        return q;
    }

    @PutMapping
    public int activateOrDeactivateQueue(SimpleCourse course) {
        return 0;
    }

    @PutMapping
    public int putTAMessage(TAMessageCourse courseAndMsg) {
        return 0;
    }

    @PostMapping("/queue-up")
    public ResponseEntity<String> queueUp(@RequestBody QueueRequest req) {
        logger.info("Received request from user: " + req.toString());
        return new ResponseEntity<>("Successfully got into  queue.", HttpStatus.CREATED);
    }
}
