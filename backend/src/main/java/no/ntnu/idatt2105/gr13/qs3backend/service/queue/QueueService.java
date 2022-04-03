package no.ntnu.idatt2105.gr13.qs3backend.service.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.queue.Queue;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.QueueRequest;
import no.ntnu.idatt2105.gr13.qs3backend.repository.queue.JdbcQueueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    @Autowired
    private JdbcQueueRepository qRepo;

    public Queue getQueueByCourse(String courseHashId) {
        return qRepo.getQueueByCourse(courseHashId);
    }


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
}
