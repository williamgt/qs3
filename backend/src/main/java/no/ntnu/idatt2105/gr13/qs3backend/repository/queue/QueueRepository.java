package no.ntnu.idatt2105.gr13.qs3backend.repository.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.queue.Queue;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.QueueRequest;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.SimpleQueueWithCourseInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QueueRepository {
    Queue getQueueByCourse(String courseHashId);

    @Transactional
    int queueUp(QueueRequest req, boolean home);

    int activateOrDeactivate(String courseHash);

    List<SimpleQueueWithCourseInfo> taGetCourses(String tAId, boolean active);

    int checkIfInQueue(String hashId, int studentId);
}
