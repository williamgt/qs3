package no.ntnu.idatt2105.gr13.qs3backend.service.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.queue.Queue;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.SimpleCourse;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.QueueRequest;
import no.ntnu.idatt2105.gr13.qs3backend.repository.queue.JdbcQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    @Autowired
    private JdbcQueueRepository qRepo;

    public Queue getQueueByCourse(String courseHashId) {
        return qRepo.getQueueByCourse(courseHashId);
    }

    public void queueUp(QueueRequest req) {
        qRepo.queueUp(req);
    }

    public int queueUp2(QueueRequest req) {
        if(req.getMessage() == null){
            req.setMessage("");
        }
        if(req.isHome()) {
            return qRepo.queueUpHome(req);
        }
        //return qRepo.queueUpCampus();
        return 0;
    }
}
