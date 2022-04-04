package no.ntnu.idatt2105.gr13.qs3backend.repository.user;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class JdbcUserRepository implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(JdbcUserRepository.class);

    /**
     * Gets id og user with given email.
     * @param email the email
     * @return the id of user with given mail. -1 if no user with given mail is found.
     */
    @Override
    public int getID(String email) {
        try{
            String query = "SELECT * from User where User.email = ?";
            UserPerson user = jdbcTemplate.queryForObject(query, (rs, rowNum) ->
                    new UserPerson(
                            rs.getString("email"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getInt("id")
                    ), email);
            return user.getId();
        }catch (IncorrectResultSetColumnCountException | EmptyResultDataAccessException e){
            logger.warn("Couldn't find user with email: " + email);
            return -1;
        }
    }

    /**
     * Gets all the registered users.
     * @return all users in DB
     */
    @Override
    public List<UserProtected> findAll() {
        return jdbcTemplate.query("SELECT * from User", (rs, rowNum) ->
                new UserProtected(
                        rs.getString("email"),
                        rs.getString("firstname"),
                        rs.getString("lastname")
                ));
    }

    /**
     * Gets more details about a user.
     * @param user the user
     * @return a detailed user
     */
    @Override
    public User getUserDetails(UserLogin user){
        return jdbcTemplate.queryForObject("SELECT * from User where User.email = ?", (rs, rowNum) ->
                new User(
                        rs.getString("email"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("id")
                ),user.getEmail());
    }

    /**
     * Returns a list of all the users in the DB with all their information.
     * @return detailed user list
     */
    @Override
    public List<User> findAllDetails() {
        return jdbcTemplate.query("SELECT * from User", (rs, rowNum) ->
                new User(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("id")
                ));
    }

    /**
     * Finds a user given an id.
     * @param id the user id
     * @return user related to the id, or null if none were found
     */
    @Override
    public UserPerson findById(long id) {
        try{
            String query = "SELECT * from User where User.id = ?";
            UserPerson user = jdbcTemplate.queryForObject(query, (rs, rowNum) ->
                    new UserPerson(
                            rs.getString("email"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getInt("id")
                    ), id);
            return user;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }

    /**
     * Checks whether any user is registered with a given mail.
     * @param email the email
     * @return true if user exists in DB, false if not
     */
    @Override
    public Boolean isUser(String email) {
        try{
            String query = "SELECT * from User where User.email = ?";
            UserPerson user = jdbcTemplate.queryForObject(query, (rs, rowNum) ->
                    new UserPerson(
                            rs.getString("email"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getInt("id")
                    ), email);
            return true;
        }catch (IncorrectResultSetColumnCountException | IncorrectResultSizeDataAccessException e){
            logger.info(email + " not registered yet");
            return false;
        }
    }

    /**
     * Finds an admin user with given id
     * @param id the id
     * @return a user if admin exists, null if not
     */
    @Override
    public User findByIdAdmin(long id) {
        try{
            String query = "SELECT * from User where User.id = ?";
            User user = jdbcTemplate.queryForObject(query, (rs, rowNum) ->
                    new User(
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getInt("id")
                    ), id);
            return user;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }

    /**
     * Registers a user in the DB. Is transactional to assure all changes are committed at once, and they are rolled back
     * if something goes wrong.
     * @param user the user to be registered
     * @return teh amount of rows affected after the transaction.
     */
    @Transactional
    @Override
    public int registerUser(User user) { //Implementation is not optimal, will get much slower over time and with large users list
        String getIdWithMail = "SELECT id from User WHERE email=?";
        String insertUser = "INSERT INTO User (firstname, lastname, email, password) VALUES(?,?,?,?)";
        Integer id;
        int rowsAffected = 0;

        try{
            id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, user.getEmail()); //Throws exception if no user has given mail
        }catch (EmptyResultDataAccessException e){
            //Guaranteed no user is registered with given mail
            rowsAffected += jdbcTemplate.update(insertUser,
                    new Object[] {user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword()});
            id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, user.getEmail());
        }
        logger.info(rowsAffected + " rows were affected by insertions into User.");
        if(id == null){
            return -1;
        }
        logger.info("Created user with id: " + id);
        return id;
    }

    /**
     * Registers a user as a teaching assistant. Is transactional and will roll back changes if something goes wrong.
     * @param taID the id of the teaching assistant
     * @return rows affected by insertion
     */
    @Transactional
    @Override
    public int makeTA(int taID) { //Implementation is not optimal, will get much slower over time and with large users list

        String insertTAUser = "INSERT IGNORE INTO TAUser (id) VALUES (?)";
        int rowsAffected = 0;
        try {
            rowsAffected += jdbcTemplate.update(insertTAUser, taID);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        logger.info(rowsAffected + " rows were affected by insertions into TAUser.");
        return rowsAffected;
    }

    /**
     * Registers a user as a teacher. Is transactional and will roll back changes if something goes wrong.
     * @param teacherID the teacher id
     * @return rows affected by insertion
     */
    @Transactional
    @Override
    public int makeTeacher(int teacherID) { //Implementation is not optimal, will get much slower over time and with large users list

        String insertTeacherUser = "INSERT IGNORE INTO TeacherUser (id) VALUES (?)";
        int rowsAffected = 0;
        try {
            rowsAffected += jdbcTemplate.update(insertTeacherUser, teacherID);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        logger.info(rowsAffected + " rows were affected by insertions into TeacherUser.");
        return rowsAffected;
    }

    /**
     * Registers a user as a student. Is transactional and will roll back changes if something goes wrong.
     * @param studentID the student id
     * @return rows affected by insertion
     */
    @Transactional
    @Override
    public int makeStudent(int studentID) { //Implementation is not optimal, will get much slower over time and with large users list

        String insertStudentUser = "INSERT IGNORE INTO StudentUser (id) VALUES (?)";
        int rowsAffected = 0;
        try {
            rowsAffected += jdbcTemplate.update(insertStudentUser, studentID);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        logger.info(rowsAffected + " rows were affected by insertions into StudentUser.");
        return rowsAffected;
    }

    /**
     * Registers a user as an admin. Is transactional and will roll back changes if something goes wrong.
     * @param adminID the admin id
     * @return rows affected by insertion
     */
    @Transactional
    @Override
    public int makeAdmin(int adminID) { //Implementation is not optimal, will get much slower over time and with large users list

        String insertStudentUser = "INSERT IGNORE INTO AdminUser (id) VALUES (?)";
        int rowsAffected = 0;
        try {
            rowsAffected += jdbcTemplate.update(insertStudentUser, adminID);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        logger.info(rowsAffected + " rows were affected by insertions into AdminUser.");
        return rowsAffected;
    }

    /**
     * Updates information about a user. Is transactional and will roll back changes if something goes wrong.
     * @param user the user
     * @return true if update successful, false if not
     */
    @Transactional
    @Override
    public Boolean updateUser(User user) {
        try{
            String query = "UPDATE User SET firstname=?, lastname=?, password=? where id=?";
            Object[] args = { user.getFirstname(), user.getLastname(), user.getPassword(), user.getId() };
            int rowsAffected = jdbcTemplate.update(query,args);
            if(rowsAffected == 1){
                return true;
            }
            logger.info("No rows were affected when updating user with id " + user.getId());
            return false;
        }catch (Exception e){
            logger.info("Something went wrong when updating user with id " + user.getId() + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a user with a given id. Is transactional and will roll back changes if something goes wrong.
     * @param id the id
     * @return true if delete successful, false if not
     */
    @Transactional
    @Override
    public Boolean deleteUser(int id) {
        try{
            String query = "DELETE FROM User WHERE id=?";
            Object[] args = { id};
            int rowsAffected = jdbcTemplate.update(query,args);
            if(rowsAffected == 1){
                return true;
            }
            logger.info("No rows were affected when deleting user with id " + id);
            return false;
        }catch (Exception e){
            logger.info("Something went wrong when updating user with id " + id + ": " + e.getMessage());
            return false;
        }
    }
}
