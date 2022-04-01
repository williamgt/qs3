package no.ntnu.idatt2105.gr13.qs3backend.controller;


import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import no.ntnu.idatt2105.gr13.qs3backend.util.FileHandler;
import no.ntnu.idatt2105.gr13.qs3backend.model.filehandler.RegisterStudent;
import no.ntnu.idatt2105.gr13.qs3backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import no.ntnu.idatt2105.gr13.qs3backend.service.security.MethodSecService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/users")
@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @Autowired
    private MethodSecService methodSecService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsersDetails(){
        List<User> users = new ArrayList<>();
        users = service.getAllUsersDetails();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')  or @methodSecService.isTargetUser(#id)")
    public ResponseEntity<UserPerson> getUserById(@PathVariable("id") long id) {
        UserPerson user = service.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getAllUserDetailsById(@PathVariable("id") long id) {
        User user = service.findByIdAdmin(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/test")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ArrayList<RegisterStudent> generateToken(@RequestParam("file")MultipartFile file) throws Exception {
        logger.info("Yes");
        return FileHandler.getArrayList(file);
    }

    @GetMapping("/testtest")
    public void test() {
        service.test();
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertUsers(@RequestBody List<User> users) {
        int rowsAffected = service.insertUsers(users);
        if(rowsAffected == 0) {
            return new ResponseEntity<>("No rows were added.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("A total of "+rowsAffected+" users were created successfully.", HttpStatus.CREATED);
        }
    }
}
