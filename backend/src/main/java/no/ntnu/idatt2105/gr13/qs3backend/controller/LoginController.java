package no.ntnu.idatt2105.gr13.qs3backend.controller;


import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService service;

//    @GetMapping("/login/{username}")
//    public ResponseEntity<User> getLoginById(@PathVariable("username") String username) {
//        User data = service.okLogin(username);
//        logger.info("Tried to retrieve data from user: " + username);
//        if(data == null)
//            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
//    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody User user) {
        try {
            if(!service.login(user)) {
                logger.info("Login failed: " + user.getUsername());
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
            }
            logger.info("Login Successful: " + user.getUsername());
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
