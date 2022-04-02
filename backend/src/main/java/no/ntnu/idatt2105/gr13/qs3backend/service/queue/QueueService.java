package no.ntnu.idatt2105.gr13.qs3backend.service.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.queue.Queue;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.SimpleCourse;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.SimpleQueue;
import no.ntnu.idatt2105.gr13.qs3backend.repository.queue.JdbcQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    @Autowired
    private JdbcQueueRepository qRepo;

    public Queue getQueueByCourse(SimpleCourse course) {
        return qRepo.getQueueByCourse(course);
    }
}
