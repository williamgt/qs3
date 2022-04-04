package no.ntnu.idatt2105.gr13.qs3backend.controller;


import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.*;
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

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/users")
@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @Autowired
    private MethodSecService methodSecService;

    /**
     * Method for admin to get all details about every user
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsersDetails(){
        List<User> users = new ArrayList<>();
        users = service.getAllUsersDetails();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Method for Target user or admin to see indo about user with given ID.
     * @param id
     * @return
     */
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

    /**
     * Method for admin to receive all details about a user with given ID
     * @param id
     * @return
     */
    @GetMapping("/user/{id}/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getAllUserDetailsById(@PathVariable("id") long id) {
        User user = service.findByIdAdmin(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Takes a file which is made into arraylist of students, then registered to db
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/upload-students-file")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ArrayList<RegisterStudent> uploadStudents(@RequestParam("file")MultipartFile file) throws Exception {
        logger.info("Received file with students.");
        try{
            ArrayList<RegisterStudent> students =  FileHandler.getArrayList(file);
            logger.info("Returning list containing "+students.size()+" students.");
            return students;
        } catch (RuntimeException e) {
            logger.info(e.getMessage());
        }
        return null;
    }

//    /**
//     * Takes a list of UserBasic and registers them in User table if they are not already registered.
//     * A UserBasic has firstname, lastname and email.
//     * A password is generated for each user as they are added to the table.
//     * @param users
//     * @return amount of rows affected
//     */
//    @PostMapping("/insert-users")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> registerUsers(@RequestBody List<UserBasic> users) {
//        logger.info("User tries to register new users.");
//        int rowsAffected = service.registerUsers(users);
//        if(rowsAffected == 0) {
//            logger.info("No rows were affected.");
//            return new ResponseEntity<>("No rows were added.", HttpStatus.OK);
//        } else {
//            logger.info(rowsAffected+" rows were affected.");
//            return new ResponseEntity<>("A total of "+rowsAffected+" users were created successfully.", HttpStatus.CREATED);
//        }
//    }

    @PostMapping("/user/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createUser(@RequestBody UserRoleString userRole) {
        logger.info(userRole.toString());
        logger.info(userRole.getRole());
        try {
            service.createUser(userRole);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("User created!",HttpStatus.CREATED);
    }

    /**
     * Takes a list of TeacherUserBasic and registers them in TeacherUser table if they are not already registered.
     * A TeacherUserBasic has firstname, lastname and email.
     * A password is generated for each user as they are added to the table.
     * @param teacherUsers
     * @return amount of rows affected
     */
    @PostMapping("/insert-teachers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registerTeacherUsers(@RequestBody List<TeacherUserBasic> teacherUsers) {
        logger.info("User tries to register new teachers.");
        int rowsAffected = service.registerTeacherUsers(teacherUsers);
        if(rowsAffected == 0) {
            logger.info("No rows were affected.");
            return new ResponseEntity<>("No rows were added.", HttpStatus.OK);
        } else {
            logger.info(rowsAffected+" rows were affected.");
            return new ResponseEntity<>("A total of "+rowsAffected+" users were created successfully.", HttpStatus.CREATED);
        }
    }

    /**
     * Takes a list of TAUserBasic and registers them in TAUser table if they are not already registered.
     * A TAUserBasic has firstname, lastname and email.
     * A password is generated for each user as they are added to the table.
     * @param taUsers
     * @return amount of rows affected
     */
    @PostMapping("/insert-tas")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<String> registerTAUsers(@RequestBody List<TAUserBasic> taUsers) {
        logger.info("User tries to register new teachers.");
        int rowsAffected = service.registerTAUsers(taUsers);
        if(rowsAffected == 0) {
            logger.info("No rows were affected.");
            return new ResponseEntity<>("No rows were added.", HttpStatus.OK);
        } else {
            logger.info(rowsAffected+" rows were affected.");
            return new ResponseEntity<>("A total of "+rowsAffected+" users were created successfully.", HttpStatus.CREATED);
        }
    }

    /**
     * Takes a list of StudentUserBasic and registers them in StudentUser table if they are not already registered.
     * A StudentUserBasic has firstname, lastname and email.
     * A password is generated for each user as they are added to the table.
     * @param studentUsers
     * @return amount of rows affected
     */
    @PostMapping("/insert-students")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<String> registerStudentUsers(@RequestBody List<StudentUserBasic> studentUsers) {
        logger.info("User tries to register new teachers.");
        int rowsAffected = service.registerStudentUsers(studentUsers);
        if(rowsAffected == 0) {
            logger.info("No rows were affected.");
            return new ResponseEntity<>("No rows were added.", HttpStatus.OK);
        } else {
            logger.info(rowsAffected+" rows were affected.");
            return new ResponseEntity<>("A total of "+rowsAffected+" users were created successfully.", HttpStatus.CREATED);
        }
    }

    /**
     * Takes a list of AdminUserBasic and registers them in AdminUser table if they are not already registered.
     * An AdminUserBasic has firstname, lastname and email.
     * A password is generated for each user as they are added to the table.
     * @param adminUsers
     * @return amount of rows affected
     */
    @PostMapping("/insert-admins")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registerAdminUsers(@RequestBody List<AdminUserBasic> adminUsers) {
        logger.info("User tries to register new teachers.");
        int rowsAffected = service.registerAdminUsers(adminUsers);
        if(rowsAffected == 0) {
            logger.info("No rows were affected.");
            return new ResponseEntity<>("No rows were added.", HttpStatus.OK);
        } else {
            logger.info(rowsAffected+" rows were affected.");
            return new ResponseEntity<>("A total of "+rowsAffected+" users were created successfully.", HttpStatus.CREATED);
        }
    }

    @PutMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN') or @methodSecService.isTargetUser(#id)")
    public ResponseEntity<Boolean> updateUser(@PathVariable("id") long id, @RequestBody User user){
        logger.info(user.getFirstname());
        logger.info(user.getLastname());
        logger.info(user.getPassword());
        logger.info(user.getEmail());
        logger.info(String.valueOf(user.getId()));
        if(service.updateUser(user)){
            logger.info("User: " + user.toString() + " updated!");
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        logger.warn("Error updating user: " + user.toString());
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
