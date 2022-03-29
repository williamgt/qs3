package no.ntnu.idatt2105.gr13.qs3backend.service;


import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserDB;
import no.ntnu.idatt2105.gr13.qs3backend.repository.JdbcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    JdbcUserRepository userRepository;

    public User okLogin(String username){
        return userRepository.findByUsername(username);
    }

    public boolean login(User login){
        return true;
    }

    public boolean checkLogin(User login){
        return false;
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        users.addAll(userRepository.findAll());
        return users;
    }

    public UserDB findById(long id) {
        UserDB user = userRepository.findById(id);
        return user;
    }

    public boolean isAdmin(User user) {
        return userRepository.isAdmin(user);
    }

    public boolean isTeacher(User user) {
        return userRepository.isAdmin(user);
    }

    public boolean isTA(User user) {
        return userRepository.isAdmin(user);
    }

}
