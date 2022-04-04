package no.ntnu.idatt2105.gr13.qs3backend.service.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.queue.Queue;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.QueueRequest;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.SimpleQueueWithCourseInfo;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserDB;
import no.ntnu.idatt2105.gr13.qs3backend.repository.queue.JdbcQueueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Queue service.
 */
@Service
public class QueueService {

    @Autowired
    private JdbcQueueRepository qRepo;

    /**
     * Gets queue of a course with given hash ID.
     *
     * @param courseHashId the course hash id
     * @return the queue related to the course
     */
    public Queue getQueueByCourse(String courseHashId) {
        return qRepo.getQueueByCourse(courseHashId);
    }


    /**
     * Queues up a student.
     *
     * @param req the queue request
     * @return the amount of rows affected
     * @throws Exception iif not course is given
     */
    public int queueUp(QueueRequest req) throws Exception {
        if(req.getMessage() == null){
            req.setMessage("");
        } else if(req.getHashId().isBlank()){
            throw new Exception("User tried to queue up for course, but did not send in an id.");
        }

        if(req.isHome()) {
            return qRepo.queueUp(req, true);
        } else {
            return qRepo.queueUp(req, false);
        }

    }

    /**
     * Activates or deactivates a queue for course with given hash ID.
     *
     * @param courseHash the course hash
     * @return the int
     */
    public int activateOrDeactivateQueue(String courseHash) {
        return qRepo.activateOrDeactivate(courseHash);
    }

    /**
     * Method for teaching assistants to get active queues they are registered in.
     *
     * @param tAId the teaching assistant id
     * @return the active queues
     */
    public List<SimpleQueueWithCourseInfo> taGetActiveQueue(String auth, String tAId) {
        return qRepo.taGetCourses(auth, tAId, true);
    }

    /**
     * Method for teaching assistants to get inactive queues they are registered in.
     *
     * @param tAId the teaching assistant id
     * @return the inactive queues
     */
    public List<SimpleQueueWithCourseInfo> taGetInactiveQueue(String auth, String tAId) {
        return qRepo.taGetCourses(auth, tAId, false);
    }

    /**
     * Checks whether a student is in a queue.
     *
     * @param hashId    the hash id of the course the queue is related to
     * @param studentId the student id
     * @return true if already in queue, false if not
     */
    public boolean studentIsInQueue(String hashId, int studentId) {
        if( qRepo.checkIfInQueue(hashId, studentId) >= 1) {
            //Already in queue, can't queue up again
            return true;
        }
        return false;
    }
}
