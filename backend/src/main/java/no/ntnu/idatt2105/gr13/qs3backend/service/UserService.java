package no.ntnu.idatt2105.gr13.qs3backend.service;


import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.*;
import no.ntnu.idatt2105.gr13.qs3backend.repository.JdbcUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    JdbcUserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserService.class);


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

    public int registerUsers(List<UserBasic> users) {
        return userRepository.registerUsers(users);
    }

    public int registerTeacherUsers(List<TeacherUserBasic> teacherUsers) {
        return userRepository.registerTeacherUsers(teacherUsers);
    }

    public int registerTAUsers(List<TAUserBasic> taUsers) {
        return userRepository.registerTAUsers(taUsers);
    }

    public int registerStudentUsers(List<StudentUserBasic> studentUsers) {
        return userRepository.registerStudentUsers(studentUsers);
    }

    public void test(){
        userRepository.test();
    }

    public int registerAdminUsers(List<AdminUserBasic> adminUsers) {
        return userRepository.registerAdminUsers(adminUsers);
    }
}
