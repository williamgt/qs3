package no.ntnu.idatt2105.gr13.qs3backend.controller.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.queue.Queue;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.SimpleCourse;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.TAMessageCourse;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.SimpleQueue;
import no.ntnu.idatt2105.gr13.qs3backend.service.queue.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping
    public Queue getQueueBySimpleCourse(SimpleCourse course) {
        logger.info("User requested queue from course " +course.getCourseCode() + ", " + course.getYear()+ ", "+course.getTerm());
        Queue q = queueService.getQueueByCourse(course);
        if(q == null) {
            logger.info("No queue for course " +course.getCourseCode() + ", " + course.getYear()+ ", "+course.getTerm() + " was found");
        } else {
            logger.info("Returning queue for course " +course.getCourseCode() + ", " + course.getYear()+ ", "+course.getTerm());
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
}
