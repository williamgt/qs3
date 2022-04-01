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
    @PreAuthorize("hasRole('ADMIN') or @methodSecService.isTargetUser(#id)")
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

    @PutMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> updateUser(@PathVariable("id") long id, @RequestBody User user){
        logger.info("Hit here");
        logger.info(user.getFirstname());
        logger.info(user.getLastname());
        logger.info(user.getPassword());
        logger.info(user.getEmail());
        logger.info(String.valueOf(user.getId()));
        if(service.updateUser(user)){
            logger.info("True");
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        logger.info("False");
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") long id){
        User user = service.findByIdAdmin(id);
        if(service.deleteUser((int) id)){
            logger.info("User: " + user.toString() + " deleted");
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        logger.info("Error deleting user: " + user.toString());
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
}
