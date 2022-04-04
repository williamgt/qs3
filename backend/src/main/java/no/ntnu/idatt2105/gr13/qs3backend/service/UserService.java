package no.ntnu.idatt2105.gr13.qs3backend.service;


import no.ntnu.idatt2105.gr13.qs3backend.model.mail.Mail;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.*;
import no.ntnu.idatt2105.gr13.qs3backend.repository.JdbcUserRepository;
import no.ntnu.idatt2105.gr13.qs3backend.service.mail.MailServiceImpl;
import no.ntnu.idatt2105.gr13.qs3backend.util.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    JdbcUserRepository userRepository;
    @Autowired
    MailServiceImpl mailService;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<UserProtected> getAllUsers(){
        List<UserProtected> users = new ArrayList<UserProtected>(userRepository.findAll());
        return users;
    }

    public List<User> getAllUsersDetails(){
        List<User> users = new ArrayList<User>(userRepository.findAllDetails());
        return users;
    }

    public UserPerson findById(long id) {
        UserPerson user = userRepository.findById(id);
        return user;
    }

    public User findByIdAdmin(long id) {
        User user = userRepository.findByIdAdmin(id);
        return user;
    }

    public Boolean updateUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return userRepository.updateUser(user);
    }

    public boolean deleteUser(int id){
        return userRepository.deleteUser(id);
    }

    public int registerUsers(List<UserBasic> users) {
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
            return -1;
        }
    }

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
            return -1;
        }
    }

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
            return -1;
        }
    }

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
            return -1;
        }
    }

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
            return -1;
        }
    }

    private boolean sendMail(Mail mail) {
        try{
            mailService.sendEmail(mail);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
