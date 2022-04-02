package no.ntnu.idatt2105.gr13.qs3backend.repository.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.Need;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.SimpleCourse;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.StudentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcQueueRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(JdbcQueueRepository.class);

    public Queue getQueueByCourse(SimpleCourse course) {
        String queueGivenCourseInfoQuery = "SELECT queueId, description, active FROM Queue WHERE courseCode=? AND year=? AND term=?"; //Put into Queue obj, then convert to SimpleQueue after
        String queueInfoGivenQueueIdQuery = "SELECT * FROM QueueInfo WHERE queueId=? AND active=1"; //Put into SimpleQueueInfo
        String studentAndQueueInfoGivenQueueInfoId = "SELECT * FROM QueueInfo " + //Put into SimpleStudentQueueInfo
                "INNER JOIN StudentQueueInfo ON QueueInfo.queueInfoId=StudentQueueInfo.queueInfoId" +
                "INNER JOIN User ON StudentQueueInfo.studentId=User.id" +
                "WHERE QueueInfo.queueInfoId=?";
        String studentRelatedToQueueInfo = "SELECT User.firstname, User.lastname, User.email, QueueInfo.queueInfoId FROM User" +
                "INNER JOIN StudentQueueInfo ON User.id=StudentQueueInfo.studentId" +
                "INNER JOIN QueueInfo ON StudentQueueInfo.queueInfoId=QueueInfo.queueInfoId";

        String studentHomeQuery = "SELECT home FROM QueueInfo " +
                "INNER JOIN Location ON QueueInfo.locationId=Location.locationId " +
                "WHERE QueueInfo.queueInfoId=?";

        String studentLocationIfNotHomeQuery = "SELECT Location.locationId, Room.roomName, Room.roomId, QueueInfo.table, " +
                "Room.floor, Building.buildingName, Building.buildingId, Campus.campusName, Campus.campusId " +
                "FROM QueueInfo " +
                "INNER JOIN Location ON QueueInfo.locationId=Location.locationId" +
                "INNER JOIN Room ON Location.roomId=Room.roomId" +
                "INNER JOIN Building ON Room.buildingId=Building.buildingId" +
                "INNER JOIN Campus ON Building.campusId=Campus.campusId" +
                "WHERE QueueInfo.queueInfoId=?";

        String tasksForStudentInQueueQuery = "SELECT Task.description FROM Task" +
                "INNER JOIN TaskQueueInfo ON Task.taskId=TaskQueueInfo.taskId" +
                "INNER JOIN QueueInfo ON TaskQueueInfo.queueInfoId=QueueInfo.queueInfoId" +
                "WHERE queueInfoId=?";

        String selectEverything = "SELECT * FROM Queue INNER JOIN QueueInfo ON Queue.queueId=QueueInfo.queueId" +
                "INNER JOIN StudentQueueInfo ON QueueInfo.queueInfoId=StudentQueueInfo.queueInfoId" +
                "INNER JOIN User ON StudentQueueInfo.studentId=User.id" +
                "INNER JOIN Location ON QueueInfo.locationId=Location.locationId" +
                "INNER JOIN Room ON Location.roomId=Room.roomId" +
                "INNER JOIN Building ON Room.buildingId=Building.buildingId" +
                "INNER JOIN Campus ON Building.campusId=Campus.campusId" +
                "WHERE Queue.courseDescription? AND Queue.year=? AND Queue.term=?";

        //Getting info for queue, have its ID here
        SimpleQueue simpleQueue;
        try {
            simpleQueue = jdbcTemplate.queryForObject(queueGivenCourseInfoQuery,
                    BeanPropertyRowMapper.newInstance(SimpleQueue.class), new Object[]{course.getCourseCode(), course.getYear(), course.getTerm()});
        } catch (IncorrectResultSizeDataAccessException e) {
            logger.info("No queue for given course: " + e.getMessage());
            return null;
        }

        //Getting every ACTIVE QueueInfo coupled to the simpleQueues ID
        List<SimpleQueueInfo> simpleQueueInfoList = jdbcTemplate.query(queueInfoGivenQueueIdQuery,
                BeanPropertyRowMapper.newInstance(SimpleQueueInfo.class), simpleQueue.getQueueId());

        //Getting a list of SimpleStudents and BareBoneLocations in the same order as the corresponding SimpleQueueInfo
        List<SimpleStudentQueueInfo> simpleStudentQueueInfoList = new ArrayList<>();
        List<BareBoneLocation> bareBoneLocationList = new ArrayList<>();
        for(SimpleQueueInfo s : simpleQueueInfoList) {
            //Extracting studentInfo
            try{
                simpleStudentQueueInfoList.add(jdbcTemplate.queryForObject(studentRelatedToQueueInfo,
                        BeanPropertyRowMapper.newInstance(SimpleStudentQueueInfo.class), s.getQueueInfoId()));
            }  catch (IncorrectResultSizeDataAccessException e) {
                logger.info("Something went wrong when extracting students in queue: " + e.getMessage());
                return null;
            }

            //Extracting location info
            try{
                Integer home = jdbcTemplate.queryForObject(studentHomeQuery, Integer.class, s.getQueueInfoId());
                if(home == 1) { //Student is home
                    bareBoneLocationList.add(new SimpleLocationHome(home));
                }else{ //Student is not home
                    SimpleLocationParts locationInfoParts =
                    jdbcTemplate.queryForObject(studentLocationIfNotHomeQuery, SimpleLocationParts.class, s.getQueueInfoId());
                    bareBoneLocationList.add(locationInfoParts);
                }
            } catch (IncorrectResultSizeDataAccessException e) {
                logger.info("Something went wrong when extracting location in queue: " + e.getMessage());
            }
        }

        //Putting all info found up to this point into a list of StudentQueueInfo that is later put in a Queue
        List<StudentQueueInfo> studentQueueInfoList = new ArrayList<>();
        for(int i = 0; i < simpleQueueInfoList.size(); i++){
            //Setting student
            studentQueueInfoList.get(i).setUser(new StudentUser(
                    simpleStudentQueueInfoList.get(i).getEmail(),
                    simpleStudentQueueInfoList.get(i).getFirstname(),
                    simpleStudentQueueInfoList.get(i).getFirstname()
            ));
            //Setting location
            if(bareBoneLocationList.get(i).getHome() == 1){
                studentQueueInfoList.get(i).setLocation(new SimpleLocation(true));
            }else{
                SimpleLocationParts loc = (SimpleLocationParts) bareBoneLocationList.get(i);
                SimpleCampus c = new SimpleCampus(loc.getCampusName(), loc.getCampusId());
                SimpleBuilding b = new SimpleBuilding(loc.getBuildingName(), loc.getBuildingId());
                SimpleRoom r = new SimpleRoom(loc.getRoomName(), loc.getRoomId(), loc.getTable(), loc.getFloor());
                studentQueueInfoList.get(i).setLocation(new SimpleLocation(c, b, r, false));
            }
            //Setting tasks
            studentQueueInfoList.get(i).setTasks(
                    jdbcTemplate.query(tasksForStudentInQueueQuery, BeanPropertyRowMapper.newInstance(Task.class), simpleQueueInfoList.get(i).getQueueInfoId())
            );
            //Setting comment
            studentQueueInfoList.get(i).setComment(simpleQueueInfoList.get(i).getComment());
            //Setting helpOrValidate
            studentQueueInfoList.get(i).setHelpOrValidate(
                    simpleQueueInfoList.get(i).getValidate() == 1 ? Need.VALID : Need.HELP);
            //Setting timeRegisteredInQueue
            studentQueueInfoList.get(i).setTimeRegisteredInQueue(simpleQueueInfoList.get(i).getStartDate());
        }

        //Creating and returning Queue
        Queue returnQ = new Queue(simpleQueue.getQueueId(), simpleQueue.isActive(), simpleQueue.getDescription());
        returnQ.setStudsInQueue(studentQueueInfoList);
        return returnQ;
    }
}
