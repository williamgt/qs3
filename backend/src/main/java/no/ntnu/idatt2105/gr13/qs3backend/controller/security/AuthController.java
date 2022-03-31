package no.ntnu.idatt2105.gr13.qs3backend.controller.security;

import no.ntnu.idatt2105.gr13.qs3backend.controller.UserController;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.Person;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.service.security.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class AuthController {

    @Autowired
    AuthService service;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<Person> login(@RequestBody User user){
        try{
            Person p = service.login(user);
            logger.info("Logged in as: " + p.getLastName() + ", " + p.getFirstName());
            return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
        }catch (Exception e){
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


}
