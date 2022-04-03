package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.*;
import no.ntnu.idatt2105.gr13.qs3backend.util.FileHandler;
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
        }catch (IncorrectResultSetColumnCountException e){
            return -1;
        }
    }

    @Override
    public List<UserProtected> findAll() {
        return jdbcTemplate.query("SELECT * from User", (rs, rowNum) ->
                new UserProtected(
                        rs.getString("email"),
                        rs.getString("firstname"),
                        rs.getString("lastname")
                ));
    }

    @Override
    public User getUserDetails(UserLogin user){
        System.out.println(user.getEmail());
        return jdbcTemplate.queryForObject("SELECT * from User where User.email = ?", (rs, rowNum) ->
                new User(
                        rs.getString("email"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("id")
                ),user.getEmail());
    }

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

    @Transactional
    @Override
    public int registerUser(User user) { //Implementation is not optimal, will get much slower over time and with large users list
        String getIdWithMail = "SELECT id from User WHERE email=?";
        String insertUser = "INSERT INTO User (firstname, lastname, email, password) VALUES(?,?,?,?)";
        Integer id;
        int rowsAffected = 0;

        try{
            id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, user.getEmail()); //Throws exception if no user has given mail
            logger.info("Gained id1: " + id);
        }catch (EmptyResultDataAccessException e){
            //Guaranteed no user is registered with given mail
            rowsAffected += jdbcTemplate.update(insertUser,
                    new Object[] {user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword()});
            id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, user.getEmail());
            logger.info("Gained id2: " + id);
        }
        logger.info(rowsAffected + " rows were affected by insertions into User.");
        if(id == null){
            return -1;
        }
        return id;
    }

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
            System.out.println(rowsAffected);
            return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

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
            System.out.println(rowsAffected);
            return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
