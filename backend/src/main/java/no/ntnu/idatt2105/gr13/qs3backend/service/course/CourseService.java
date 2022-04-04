package no.ntnu.idatt2105.gr13.qs3backend.service.course;

import no.ntnu.idatt2105.gr13.qs3backend.model.course.Course;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.CourseForm;
import no.ntnu.idatt2105.gr13.qs3backend.model.mail.Mail;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.StudentUserBasic;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.TAUserBasic;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.TeacherUserBasic;
import no.ntnu.idatt2105.gr13.qs3backend.model.course.SimpleCourseWithName;
import no.ntnu.idatt2105.gr13.qs3backend.repository.course.JdbcCourseRepository;
import no.ntnu.idatt2105.gr13.qs3backend.repository.user.JdbcUserRepository;
import no.ntnu.idatt2105.gr13.qs3backend.service.mail.MailServiceImpl;
import no.ntnu.idatt2105.gr13.qs3backend.util.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for CourseController
 */
@Service
public class CourseService {
    Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private JdbcCourseRepository courseRepo;

    @Autowired
    private JdbcUserRepository userRepo;

    @Autowired
    MailServiceImpl mailService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public int updateCourse(String courseCode, Course course) { //TODO add checks and trim strings etc
        Course getCourse = courseRepo.getCourseByCode(courseCode);
        if(getCourse == null) {
            logger.info("Not able to update course with code " + courseCode + ", not found in db");
            return -1;
        }
        else { //TODO add checks
            return courseRepo.updateCourse(courseCode, course);
        }
    }

    /**
     * Registers a course and Users of different types. Each call inside the method is tagged with Transactional,
     * but registerCourse in its entirety is NOT transactional. This is a weakness. Could have put functionality
     * for registering Users of various kinds in insertCourse from JdbcCourseRepository, but it fits better in
     * JdbcUserRepository.
     * @param course
     */
    public int registerCourse(CourseForm course) {
        logger.info("Registering Students...");
        int rowsAffectedStuds = registerStudents(course.getStudents());
        logger.info("Registering Teachers...");
        int rowsAffectedTeachers = registerTeachers((ArrayList<TeacherUserBasic>) course.getTeachers());
        logger.info("Registering TAs...");
        int rowsAffectedTAs = registerTAs((ArrayList<TAUserBasic>) course.getTas());

        logger.info("Registering Course...");
        int rowsAffectedCourse = courseRepo.insertCourse(course);
        return rowsAffectedStuds + rowsAffectedTeachers + rowsAffectedTAs + rowsAffectedCourse;
    }

    private boolean sendMail(Mail mail) {
        try{
            mailService.sendEmail(mail);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Course getCourseByCourseCode(String courseCode) {
        return courseRepo.getCourseByCode(courseCode);
    }

    /**
     * Private method for registering teachers. Similar to the one in UserService
     * @param teachers
     * @return
     */
    private int registerTeachers(ArrayList<TeacherUserBasic> teachers){
        int rows = 0;
        boolean sendMail = false;
        try {
            for (TeacherUserBasic t : teachers) {
                String psw = FileHandler.getRandomPassword();
                User teacher = new User(t.getEmail(), passwordEncoder.encode(psw), t.getFirstname(), t.getLastname());

                sendMail = userRepo.isUser(t.getEmail());

                int id = userRepo.registerUser(teacher);
                if(id == -1){
                    return -1;
                }
                rows += userRepo.makeTeacher(id);

                if(!sendMail){
                    logger.info("Sending mail to: " + t.getEmail());
                    sendMail(new Mail(t.getEmail(), psw));
                }
            }
            return rows;
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * Private method for registering ta. Similar to the one in UserService
     * @param tas
     * @return
     */
    private int registerTAs(ArrayList<TAUserBasic> tas){
        int rows = 0;
        boolean sendMail = false;
        try {
            for (TAUserBasic t : tas) {
                String psw = FileHandler.getRandomPassword();
                User teacher = new User(t.getEmail(), passwordEncoder.encode(psw), t.getFirstname(), t.getLastname());

                sendMail = userRepo.isUser(t.getEmail());

                int id = userRepo.registerUser(teacher);
                if(id == -1){
                    return -1;
                }
                rows += userRepo.makeTA(id);

                if(!sendMail){
                    logger.info("Sending mail to: " + t.getEmail());
                    sendMail(new Mail(t.getEmail(), psw));
                }
            }
            return rows;
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * Private method for registering students. Similar to the one in UserService
     * @param studentUsers
     * @return
     */
    private int registerStudents(List<StudentUserBasic> studentUsers) {
        int rows = 0;
        boolean sendMail = false;
        try {
            for (StudentUserBasic s : studentUsers) {
                String psw = FileHandler.getRandomPassword();
                User student = new User(s.getEmail(), passwordEncoder.encode(psw), s.getFirstname(), s.getLastname());

                sendMail = userRepo.isUser(s.getEmail());

                int id = userRepo.registerUser(student);
                if(id == -1){
                    return -1;
                }
                rows += userRepo.makeStudent(id);
                if(!sendMail){
                    logger.info("Sending mail to: " + s.getEmail());
                    sendMail(new Mail(s.getEmail(), psw));
                }

            }
            return rows;
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * Method for retrieving active courses
     * @param id
     * @return
     */
    public List<SimpleCourseWithName> getActiveCourses(int id) {
        return courseRepo.getActiveOrInactiveCoursesByUserId(id, true);
    }

    /**
     * Method for retrieving inactive courses
     * @param id
     * @return
     */
    public List<SimpleCourseWithName> getInactiveCourses(int id) {
        return courseRepo.getActiveOrInactiveCoursesByUserId(id, false);
    }
}
