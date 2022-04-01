package no.ntnu.idatt2105.gr13.qs3backend.controller.security;

import no.ntnu.idatt2105.gr13.qs3backend.controller.UserController;
import no.ntnu.idatt2105.gr13.qs3backend.model.security.Role;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserLogin;
import no.ntnu.idatt2105.gr13.qs3backend.service.security.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class AuthController {

    @Autowired
    AuthService service;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLogin user){
        try{
            User p = service.login(user);
            logger.info("Logged in as: " + p.getLastname() + ", " + p.getFirstname());
            return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
        }catch (Exception e){
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/getRole")
    public ResponseEntity<Role> getRole(@RequestBody User user){
        Role role = service.getRoleNotProt(user);
        logger.info("Retrieved role: " + role + " For user: + " + user.toString());
        if(!role.equals(Role.UNDEFINED))
            return new ResponseEntity<>(role, HttpStatus.ACCEPTED);

        logger.info("Could not define role for: " + user.toString());
        return new ResponseEntity<>(role, HttpStatus.BAD_REQUEST);
    }
}
