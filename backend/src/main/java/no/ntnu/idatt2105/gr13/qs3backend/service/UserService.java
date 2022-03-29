package no.ntnu.idatt2105.gr13.qs3backend.service;


import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
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
        User _login = userRepository.findByUsername(login.getUsername());
        if(_login == null || !login.getPassword().equals(_login.getPassword())) {
            return false;
        }
        return true;
    }

    public boolean checkLogin(User login){
        User _login = userRepository.findByUsername(login.getUsername());
        return _login != null && login.getPassword().equals(_login.getPassword());
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        users.addAll(userRepository.findAll());
        return users;
    }

    public User findById(long id) {
        User user = userRepository.findById(id);
        return user;
    }
}
