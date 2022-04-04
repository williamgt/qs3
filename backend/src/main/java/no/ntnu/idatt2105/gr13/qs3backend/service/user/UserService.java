package no.ntnu.idatt2105.gr13.qs3backend.service.user;


import no.ntnu.idatt2105.gr13.qs3backend.model.mail.Mail;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.*;
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
import java.util.Objects;

/**
 * Service for UserController
 */
@Service
public class UserService {
    @Autowired
    JdbcUserRepository userRepository;
    @Autowired
    MailServiceImpl mailService;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    //Used to encrypt passwords securely before sending to Repo
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Method for getting basic info about all users
     * @return
     */
    public List<UserProtected> getAllUsers(){
        List<UserProtected> users = new ArrayList<UserProtected>(userRepository.findAll());
        return users;
    }

    /**
     * Method for getting all users with all details
     * @return
     */
    public List<User> getAllUsersDetails(){
        List<User> users = new ArrayList<User>(userRepository.findAllDetails());
        return users;
    }

    /**
     * Method for getting specific User based on ID
     * @param id
     * @return
     */
    public UserPerson findById(long id) {
        UserPerson user = userRepository.findById(id);
        return user;
    }

    /**
     * Method for getting all info about specific user (admin only)
     * @param id
     * @return
     */
    public User findByIdAdmin(long id) {
        User user = userRepository.findByIdAdmin(id);
        return user;
    }

    /**
     * Method for updating existing user (admin or current user only)
     * @param user
     * @return
     */
    public Boolean updateUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.updateUser(user);
    }

    /**
     * Method for deleting user. TODO Make it possible to remove user even if registered in queue etc
     * @param id
     * @return
     */
    public boolean deleteUser(int id){
        return userRepository.deleteUser(id);
    }

    /**
     * Method for registering Users. Should never alone! No one should be registered as user only
     * @param users
     * @return
     */
    private int registerUsers(List<UserBasic> users) {
        int rows = 0;
        boolean sendMail = false;
        try {
            for (UserBasic u : users) {
                String psw = FileHandler.getRandomPassword();
                User teacher = new User(u.getEmail(), passwordEncoder.encode(psw), u.getFirstname(), u.getLastname());
                sendMail = userRepository.isUser(u.getEmail());
                int id = userRepository.registerUser(teacher);
                if(id == -1){
                    return -1;
                };
                if(!sendMail){
                    logger.info("Sending mail to: " + u.getEmail());
                    sendMail(new Mail(u.getEmail(), psw));
                }
                rows++;
            }
            return rows;
        }catch (Exception e){
            logger.info(e.getMessage());
            logger.warn("Failed to register users: " + e.getMessage());
            logger.warn("Happened in row: " + rows);
            return -1;
        }
    }

    /**
     * Method for registering teachers. If teacher already registered do nothing
     * If registered for the first time send mail to teacherEmail with info about new random password
     * @param teacherUsers ArrayList
     * @return
     */
    public int registerTeacherUsers(List<TeacherUserBasic> teacherUsers) {
        int rows = 0;
        boolean sendMail = false;
        try {
            for (TeacherUserBasic t : teacherUsers) {
                String psw = FileHandler.getRandomPassword();
                User teacher = new User(t.getEmail(), passwordEncoder.encode(psw), t.getFirstname(), t.getLastname());

                sendMail = userRepository.isUser(t.getEmail());

                int id = userRepository.registerUser(teacher);
                if(id == -1){
                    return -1;
                }
                rows += userRepository.makeTeacher(id);

                if(!sendMail){
                    logger.info("Sending mail to: " + t.getEmail());
                    sendMail(new Mail(t.getEmail(), psw));
                }
            }
            return rows;
        }catch (Exception e){
            logger.warn("Failed to register teacher users: " + e.getMessage());
            logger.warn("Happened in row: " + rows);
            return -1;
        }
    }

    /**
     * Method for registering TAs. If already registered do nothing
     * If not send mail to given email about random password for first log-in
     * @param taUsers
     * @return
     */
    public int registerTAUsers(List<TAUserBasic> taUsers) {
        int rows = 0;
        boolean sendMail = false;
        try {
            for (TAUserBasic t : taUsers) {
                String psw = FileHandler.getRandomPassword();
                User ta = new User(t.getEmail(), passwordEncoder.encode(psw), t.getFirstname(), t.getLastname());

                sendMail = userRepository.isUser(t.getEmail());

                int id = userRepository.registerUser(ta);
                if(id == -1){
                    return -1;
                }
                rows += userRepository.makeTA(id);
                if(!sendMail){
                    logger.info("Sending mail to: " + t.getEmail());
                    sendMail(new Mail(t.getEmail(), psw));
                }

            }
            return rows;
        }catch (Exception e){
            logger.warn("Failed to register TA users: " + e.getMessage());
            logger.warn("Happened in row: " + rows);
            return -1;
        }
    }

    /**
     * Method for registering Students. If already registered do nothing
     * If not send mail to given email about random password for first log-in
     * @param studentUsers
     * @return
     */
    public int registerStudentUsers(List<StudentUserBasic> studentUsers) {
        int rows = 0;
        boolean sendMail = false;
        try {
            for (StudentUserBasic s : studentUsers) {
                String psw = FileHandler.getRandomPassword();
                User student = new User(s.getEmail(), passwordEncoder.encode(psw), s.getFirstname(), s.getLastname());

                sendMail = userRepository.isUser(s.getEmail());

                int id = userRepository.registerUser(student);
                if(id == -1){
                    return -1;
                }
                rows += userRepository.makeStudent(id);
                if(!sendMail){
                    logger.info("Sending mail to: " + s.getEmail());
                    sendMail(new Mail(s.getEmail(), psw));
                }

            }
            return rows;
        }catch (Exception e){
            logger.warn("Failed to register student users: " + e.getMessage());
            logger.warn("Happened in row: " + rows);
            return -1;
        }
    }

    /**
     * Method for registering admins. If already registered do nothing
     * If not send mail to given email about random password for first log-in
     * @param adminUsers
     * @return
     */
    public int registerAdminUsers(List<AdminUserBasic> adminUsers) {
        int rows = 0;
        boolean sendMail = false;
        try {
            for (AdminUserBasic a : adminUsers) {
                String psw = FileHandler.getRandomPassword();
                User admin = new User(a.getEmail(), passwordEncoder.encode(psw), a.getFirstname(), a.getLastname());

                sendMail = userRepository.isUser(a.getEmail());

                int id = userRepository.registerUser(admin);
                if(id == -1){
                    return -1;
                }
                rows += userRepository.makeAdmin(id);
                if(!sendMail){
                    logger.info("Sending mail to: " + a.getEmail());
                    sendMail(new Mail(a.getEmail(), psw));
                }

            }
            return rows;
        }catch (Exception e){
            logger.warn("Failed to register admin users: " + e.getMessage());
            logger.warn("Happened in row: " + rows);
            return -1;
        }
    }
    public int createUser(UserRoleString userRole){
        if(userRepository.getID(userRole.getEmail()) != -1){
            throw new IllegalArgumentException("Email already registered");
        }
        sendMail(new Mail(userRole.getEmail(), userRole.getPassword())); //TODO ENABLE
        User user = new User(userRole.getEmail(), passwordEncoder.encode(userRole.getPassword()), userRole.getFirstname(), userRole.getLastname());
        userRole.setPassword(passwordEncoder.encode(userRole.getPassword()));
        int id = userRepository.registerUser(user);
        if(Objects.equals(userRole.getRole(), "ADMIN"))
            return userRepository.makeAdmin(id);
        if (Objects.equals(userRole.getRole(), "TEACHER"))
            return userRepository.makeTeacher(id);
        if (Objects.equals(userRole.getRole(), "TA"))
            return userRepository.makeTA(id);
        if (Objects.equals(userRole.getRole(), "STUDENT"))
            return userRepository.makeStudent(id);

        userRepository.deleteUser(id);

        throw new IllegalArgumentException("Couldn't create user!");
    }

    /**
     * Method to send mail
     * @param mail
     */
    private void sendMail(Mail mail) {
        try{
            mailService.sendEmail(mail);
        }catch (Exception e){
            logger.warn("Failed to send mail to: " + mail.getMailTo());
            logger.warn("Error message: " + e.getMessage());
        }
    }

}
