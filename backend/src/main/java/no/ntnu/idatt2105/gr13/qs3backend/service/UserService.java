package no.ntnu.idatt2105.gr13.qs3backend.service;


import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import no.ntnu.idatt2105.gr13.qs3backend.repository.JdbcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    JdbcUserRepository userRepository;

    public List<UserProtected> getAllUsers(){
        List<UserProtected> users = new ArrayList<UserProtected>(userRepository.findAll());
        return users;
    }

    public List<UserPersonAll> getAllUsersDetails(){
        List<UserPersonAll> users = new ArrayList<UserPersonAll>(userRepository.findAllDetails());
        return users;
    }

    public UserPerson findById(long id) {
        UserPerson user = userRepository.findById(id);
        return user;
    }

    public UserPersonAll findByIdAdmin(long id) {
        UserPersonAll user = userRepository.findByIdAdmin(id);
        return user;
    }


}
