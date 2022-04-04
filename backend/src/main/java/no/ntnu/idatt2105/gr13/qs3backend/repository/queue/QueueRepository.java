package no.ntnu.idatt2105.gr13.qs3backend.repository.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.queue.Queue;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.QueueRequest;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.SimpleQueueWithCourseInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The interface for the Queue repository.
 */
public interface QueueRepository {
    /**
     * Gets queue by course.
     *
     * @param courseHashId the course hash id
     * @return the queue by course
     */
    Queue getQueueByCourse(String courseHashId);

    /**
     * Method for queueing up-
     *
     * @param req  the req
     * @param home the home
     * @return the int
     */
    @Transactional
    int queueUp(QueueRequest req, boolean home);

    /**
     * Activate or deactivate a course queue.
     *
     * @param courseHash the course hash
     * @return the int
     */
    int activateOrDeactivate(String courseHash);

    /**
     * Method for TA to get inactive or active courses related to them.
     *
     * @param tAId   the t a id
     * @param active the active
     * @return the list
     */
    List<SimpleQueueWithCourseInfo> taGetCourses(String auth, String tAId, boolean active);

    /**
     * Checks if a student is in a course queue.
     *
     * @param hashId    the hash id
     * @param studentId the student id
     * @return the int
     */
    int checkIfInQueue(String hashId, int studentId);
}
