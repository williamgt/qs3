package no.ntnu.idatt2105.gr13.qs3backend.repository.queue;

import no.ntnu.idatt2105.gr13.qs3backend.model.Need;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.queue.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.SimpleCourse;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskWithId;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskWithNums;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.StudentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcQueueRepository implements QueueRepository{
    Logger logger = LoggerFactory.getLogger(JdbcQueueRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Returns a queue and all of it's related information based on the hash ID of a course.
     * @param courseHashId the course hash id
     * @return queue related to course
     */
    @Override
    public Queue getQueueByCourse(String courseHashId) {
        String courseGivenCourseHashQuery = "SELECT courseCode, year, term FROM Course WHERE hashId=?";
        String queueGivenCourseInfoQuery = "SELECT queueId, description, active FROM Queue WHERE courseCode=? AND year=? AND term=?"; //Put into Queue obj, then convert to SimpleQueue after
        String queueInfoGivenQueueIdQuery = "SELECT * FROM QueueInfo WHERE queueId=? AND active=1"; //Put into SimpleQueueInfo
        String studentRelatedToQueueInfo = "SELECT User.firstname, User.lastname, User.email, QueueInfo.queueInfoId FROM User " +
                "INNER JOIN StudentQueueInfo ON User.id=StudentQueueInfo.studentId " +
                "INNER JOIN QueueInfo ON StudentQueueInfo.queueInfoId=QueueInfo.queueInfoId " +
                "WHERE QueueInfo.queueInfoId=?";

        String studentHomeQuery = "SELECT home FROM QueueInfo " +
                "INNER JOIN Location ON QueueInfo.locationId=Location.locationId " +
                "WHERE QueueInfo.queueInfoId=?";

        String studentLocationIfNotHomeQuery = "SELECT Location.locationId, Room.roomName, Room.roomId, QueueInfo.table, " +
                "Room.floor, Building.buildingName, Building.buildingId, Campus.campusName, Campus.campusId " +
                "FROM QueueInfo " +
                "INNER JOIN Location ON QueueInfo.locationId=Location.locationId " +
                "INNER JOIN Room ON Location.roomId=Room.roomId " +
                "INNER JOIN Building ON Room.buildingId=Building.buildingId " +
                "INNER JOIN Campus ON Building.campusId=Campus.campusId " +
                "WHERE QueueInfo.queueInfoId=?";

        String tasksForStudentInQueueQuery = "SELECT Task.description FROM Task " +
                "INNER JOIN TaskQueueInfo ON Task.taskId=TaskQueueInfo.taskId " +
                "INNER JOIN QueueInfo ON TaskQueueInfo.queueInfoId=QueueInfo.queueInfoId " +
                "WHERE QueueInfo.queueInfoId=?";

        //Finding course
        SimpleCourse course;
        try {
            course = jdbcTemplate.queryForObject(courseGivenCourseHashQuery,
                    BeanPropertyRowMapper.newInstance(SimpleCourse.class), courseHashId);
        } catch (IncorrectResultSizeDataAccessException e) {
            logger.info("No course for hash id "+courseHashId+" found: "+ e.getMessage());
            return null;
        }
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
                    jdbcTemplate.queryForObject(studentLocationIfNotHomeQuery, (rs, rowNum) ->
                        new SimpleLocationParts(
                                rs.getInt("Location.locationId"),
                                rs.getString("Room.roomName"),
                                rs.getInt("Room.roomId"),
                                rs.getInt("QueueInfo.table"),
                                rs.getInt("Room.floor"),
                                rs.getString("Building.buildingName"),
                                rs.getInt("Building.buildingId"),
                                rs.getString("Campus.campusName"),
                                rs.getInt("Campus.campusId")
                    ), s.getQueueInfoId());
                    bareBoneLocationList.add(locationInfoParts);
                }
            } catch (IncorrectResultSizeDataAccessException e) {
                logger.info("Something went wrong when extracting location in queue: " + e.getMessage());
            }
        }

        //Putting all info found up to this point into a list of StudentQueueInfo that is later put in a Queue
        List<StudentQueueInfo> studentQueueInfoList = new ArrayList<StudentQueueInfo>();
        SimpleCampus c;
        SimpleBuilding b;
        SimpleRoom r;
        SimpleLocation s;
        for(int i = 0; i < simpleQueueInfoList.size(); i++){
            //Setting student
            studentQueueInfoList.add(new StudentQueueInfo());
            studentQueueInfoList.get(i).setUser(new StudentUser(
                    simpleStudentQueueInfoList.get(i).getEmail(),
                    simpleStudentQueueInfoList.get(i).getFirstname(),
                    simpleStudentQueueInfoList.get(i).getLastname(),
                    simpleStudentQueueInfoList.get(i).getQueueInfoId()
            ));
            //Setting location
            if(bareBoneLocationList.get(i).getHome() == 1){
                studentQueueInfoList.get(i).setLocation(new SimpleLocation(true));
            }else{
                SimpleLocationParts loc = (SimpleLocationParts) bareBoneLocationList.get(i);
                c = new SimpleCampus(loc.getCampusName(), loc.getCampusId());
                b = new SimpleBuilding(loc.getBuildingName(), loc.getBuildingId());
                r = new SimpleRoom(loc.getRoomId(), loc.getTable(), loc.getRoomName(), loc.getFloor());
                s = new SimpleLocation(c, b, r, false);
                studentQueueInfoList.get(i).setLocation(s);
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
        if(!returnQ.setStudsInQueue(studentQueueInfoList)){
            logger.info("Something went wrong when adding student queue info to queue.");
        }

        return returnQ;
    }

    /**
     * Method for letting a student queue up in some queue that is found in the request. The request contains all the
     * information related to the queueing up action, such as who the student is, which course they are queueing up for
     * which tasks they want validated etc. See javadoc in QueueRequest for more info. Home boolean decides whether
     * the student is home or not. Method is transactional such that any changes is rolled back if something goes wrong
     * during the insertion.
     * @param req  the request
     * @param home whether the student is home or not
     * @return amount of rows affected
     */
    @Override
    @Transactional
    public int queueUp(QueueRequest req, boolean home) {
        String queueIdGivenCourseHashId = "SELECT Queue.queueId FROM Course " +
                "INNER JOIN Queue ON Course.courseCode=Queue.courseCode AND Course.year=Queue.year AND Course.term=Queue.term " +
                "WHERE Course.hashId=?";

        String insertIntoQueueInfo = "INSERT INTO QueueInfo (validate, locationId, comment, queueId, `table`) VALUES (?,?,?,?,?)";

        String getLocationIdQuery = "SELECT Location.locationId FROM Location " +
                "INNER JOIN Room ON Location.roomId=Room.roomId " +
                "INNER JOIN Building ON Room.buildingId=Building.buildingId " +
                "INNER JOIN Campus ON Building.campusId=Campus.campusId " +
                "WHERE Campus.campusId=? AND Building.buildingId=? AND Room.roomId=?";

        String insertLocationIfNotExistsQuery = "INSERT INTO Location (roomId) VALUES (?)";

        String insertIntoStudentQueueInfo = "INSERT INTO StudentQueueInfo (queueInfoId, studentId) VALUES (?,?)";

        String listOfTasksForCourseQuery = "SELECT taskId, description FROM Task " +
                "INNER JOIN TaskSet ON Task.taskSetId=TaskSet.taskSetId " +
                "INNER JOIN Tasks ON TaskSet.tasksId=Tasks.tasksId " +
                "INNER JOIN Course ON Tasks.courseCode=Course.courseCode AND Tasks.year=Course.year AND Tasks.term=Course.term " +
                "WHERE Course.hashId=?";

        String insertIntoTaskQueueInfoQuery = "INSERT INTO TaskQueueInfo (taskId, queueInfoId) VALUES (?,?)";

        int totalRowsAffected = 0;
        logger.info("Trying to get id...");
        Integer queueId = jdbcTemplate.queryForObject(queueIdGivenCourseHashId, Integer.class ,
                new Object[]{req.getHashId()});
        logger.info("got id");

        int validate = req.isVali() ? 1 : 0; //Setting validate int based on given boolean
        KeyHolder queueInfoKeyHolder = new GeneratedKeyHolder(); //Saving auto generated id for QueueInfo here
        //int locationId;
        int table;

        //Student is home
        if(home) {
            int locationId = 1; //locationId is always 1 for home
            table = 0; //No table if home

            //Inserting into QueueInfo:
            totalRowsAffected += jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertIntoQueueInfo, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, validate);
                ps.setInt(2, locationId);
                ps.setString(3, req.getMessage());
                ps.setInt(4, queueId);
                ps.setInt(5, table);
                return ps;
            }, queueInfoKeyHolder);
            logger.info(totalRowsAffected + " total rows affected after inserting into QueueInfo.");
        }

        //Student is not home
        else {
            //Need to find and potentially insert location since not at home
            table = req.getTable();
            int locationId;
            try {
                locationId = jdbcTemplate.queryForObject(getLocationIdQuery,
                        Integer.class, new Object[] {req.getCampusId(), req.getBuildingId(), req.getRoomId()});
            } catch (IncorrectResultSizeDataAccessException e){
                logger.info("No location with campusId " + req.getCampusId() + ", buildingId " + req.getBuildingId() + ", roomId " + req.getRoomId() + " exists, inserting now.");
                KeyHolder locationIdHolder = new GeneratedKeyHolder(); //Saving auto generated id for location here
                totalRowsAffected += jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(insertLocationIfNotExistsQuery, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, req.getRoomId());
                    return ps;
                }, locationIdHolder);
                locationId = locationIdHolder.getKey().intValue();
            }

            //Inserting into QueueInfo:
            int finalLocationId = locationId;
            totalRowsAffected += jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertIntoQueueInfo, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, validate);
                ps.setInt(2, finalLocationId);
                ps.setString(3, req.getMessage());
                ps.setInt(4, queueId);
                ps.setInt(5, table);
                return ps;
            }, queueInfoKeyHolder);
            logger.info(totalRowsAffected + " total rows affected after inserting into QueueInfo.");
        }

        int queueInfoId = queueInfoKeyHolder.getKey().intValue(); //Getting primary key for created QueueInfo
        logger.info("Queueinfoid: "+queueInfoId + " and stucent id : " + req.getUser().getId());

        //Inserting into StudentQueueInfo:
        totalRowsAffected += jdbcTemplate.update(insertIntoStudentQueueInfo, queueInfoId, req.getUser().getId());
        logger.info(totalRowsAffected + " total rows affected after inserting into StudentQueueInfo.");

        //Getting all tasks and inserting into tables related to Tasks:
        List<TaskWithId> allTasksInCourse = jdbcTemplate.query(listOfTasksForCourseQuery,
                BeanPropertyRowMapper.newInstance(TaskWithId.class), req.getHashId());


        if(allTasksInCourse.isEmpty()) logger.info("No tasks in course with hash " + req.getHashId());
        else {
            for(TaskWithId t : allTasksInCourse) {
                for(TaskWithNums tUser : req.getTask()) {
                    if(t.getDescription().trim().equalsIgnoreCase(tUser.getDescription().trim())){
                        totalRowsAffected += jdbcTemplate.update(insertIntoTaskQueueInfoQuery, new Object[] {t.getTaskId(), queueInfoId});
                    }
                }
            }
            logger.info(totalRowsAffected + " total rows affected after inserting into TaskQueueInfo.");
        }

        return totalRowsAffected;
    }

    /**
     * Either activates or deactivates a course's queue based on a course's given hash ID. Will only update one row,
     * and is therefore not transactional.
     * @param courseHash the course hash ID for the queue to activate/deactivate
     * @return 1 if everything went ok and queue is activated/deactivated, -1 if no course or queue was found. 0 If no
     *              rows were affected
     */
    @Override
    public int activateOrDeactivate(String courseHash) {
        String selectCourseQuery = "SELECT courseCode, year, term FROM Course WHERE hashId=?";
        String getStateOfQueueQuery = "SELECT active FROM Queue WHERE Queue.courseCode=? AND Queue.year=? AND Queue.term=?";
        String updateQueueQuery = "UPDATE Queue SET active=? WHERE Queue.courseCode=? AND Queue.year=? AND Queue.term=?";

        SimpleCourse course;
        try{
            course = jdbcTemplate.queryForObject(selectCourseQuery,
                    BeanPropertyRowMapper.newInstance(SimpleCourse.class), courseHash);
        } catch (IncorrectResultSizeDataAccessException e) {
            logger.info("Something went wrong when getting course with hash " + courseHash + ": " + e.getMessage());
            return -1;
        }

        Integer activeInt;
        try{
            activeInt = jdbcTemplate.queryForObject(getStateOfQueueQuery,
                    Integer.class, new Object[] {course.getCourseCode(), course.getYear(), course.getTerm()});
        }catch (IncorrectResultSizeDataAccessException e) {
            logger.info("Something went wrong when getting state of queue related to course with hash " + courseHash + ": " + e.getMessage());
            return -1;
        }
        int rowsAffected = 0;
        if(activeInt.intValue() == 1){ //Queue is active, need to deactivate
            rowsAffected += jdbcTemplate.update(updateQueueQuery,
                    new Object[]{0, course.getCourseCode(), course.getYear(), course.getTerm()});
        } else {//Queue is inactive, need to activate
            rowsAffected += jdbcTemplate.update(updateQueueQuery,
                    new Object[]{1, course.getCourseCode(), course.getYear(), course.getTerm()});
        }
        return rowsAffected;
    }

    /**
     * Gets all courses that a teaching assistant is linked to, with either active or inactive queue.
     * @param tAId   the teaching assistants id
     * @param active boolean deciding if courses with active or inactive queues are returned
     * @return list of simple queues that are either active or inactive with related course info
     */
    @Override
    public List<SimpleQueueWithCourseInfo> taGetCourses(String auth, String tAId, boolean active) {
        String selectCourseQuery;

        if(auth.equals("TEACHER")){
            selectCourseQuery = "SELECT Queue.queueId, Queue.description, Queue.courseCode, Queue.year, Queue.term, Queue.active, Course.hashId, Course.courseName " +
                    "FROM Queue " +
                    "INNER JOIN Course ON Queue.courseCode=Course.courseCode AND Queue.year=Course.year AND Queue.term=Course.term " +
                    "INNER JOIN TeacherCourse ON TeacherCourse.courseCode=Course.courseCode AND TeacherCourse.year=Course.year AND TeacherCourse.term=Course.term " +
                    "WHERE TeacherCourse.teacherId=? AND Queue.active=?";
        }else {
            selectCourseQuery = "SELECT Queue.queueId, Queue.description, Queue.courseCode, Queue.year, Queue.term, Queue.active, Course.hashId, Course.courseName " +
                    "FROM Queue " +
                    "INNER JOIN Course ON Queue.courseCode=Course.courseCode AND Queue.year=Course.year AND Queue.term=Course.term " +
                    "INNER JOIN TACourse ON TACourse.courseCode=Course.courseCode AND TACourse.year=Course.year AND TACourse.term=Course.term " +
                    "WHERE TACourse.tAId=? AND Queue.active=?";
        }

        List<SimpleQueueWithCourseInfo> qs = jdbcTemplate.query(selectCourseQuery,
                BeanPropertyRowMapper.newInstance(SimpleQueueWithCourseInfo.class), new Object[] {tAId, active == true ? 1 : 0});

        return qs;
    }

    /**
     * Checks if a student is already in a queue of some course with given hash ID.
     * @param hashId    the course hash id
     * @param studentId the student id
     * @return the count of instances where a student is in a queue for a given course. Expected max 1 for in queue, 0 if not in queue.
     */
    @Override
    public int checkIfInQueue(String hashId, int studentId) {
        String countActiveQuery = "SELECT COUNT(QueueInfo.active) FROM Course " +
                "INNER JOIN Queue ON Course.courseCode=Queue.courseCode AND Course.year=Queue.year AND Course.term=Queue.term " +
                "INNER JOIN QueueInfo ON Queue.queueId=QueueInfo.queueId " +
                "INNER JOIN StudentQueueInfo ON QueueInfo.queueInfoId=StudentQueueInfo.queueInfoId " +
                "WHERE Course.hashId=? AND StudentQueueInfo.studentId=? AND QueueInfo.active=1";

        //If one (or greater) already in queue, if 0 not in queue
        return jdbcTemplate.queryForObject(countActiveQuery, Integer.class, new Object[]{hashId, studentId});
    }
}
