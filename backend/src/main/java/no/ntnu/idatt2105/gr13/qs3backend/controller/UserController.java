package no.ntnu.idatt2105.gr13.qs3backend.controller;


import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserPerson;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserPersonAll;
import no.ntnu.idatt2105.gr13.qs3backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/users")
@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<UserPerson>> getAllUsers(){
        List<UserPerson> users = new ArrayList<>();
        users = service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserPerson> getUserById(@PathVariable("id") long id) {
        UserPerson user = service.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{id}/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserPersonAll> getAllUserDetailsById(@PathVariable("id") long id) {
        UserPersonAll user = service.findByIdAdmin(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
