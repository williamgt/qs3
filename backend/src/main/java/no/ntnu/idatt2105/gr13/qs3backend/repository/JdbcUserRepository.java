package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.*;
import no.ntnu.idatt2105.gr13.qs3backend.util.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
    public User findByUsername(String username) {
        return null;
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
    public int registerUsers(List<UserBasic> users) { //Implementation is not optimal, will get much slower over time and with large users list
        String getIdWithMail = "SELECT id from User WHERE email=?";
        String insertUser = "INSERT INTO User (firstname, lastname, email, password) VALUES(?,?,?,?)";
        Integer id;
        int rowsAffected = 0;

        for(int i = 0; i < users.size(); i++) {
            try{
                id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, users.get(i).getEmail()); //Throws exception if no user has given mail
            }catch (EmptyResultDataAccessException e){
                //Guaranteed no user is registered with given mail
                rowsAffected += jdbcTemplate.update(insertUser,
                        new Object[] {users.get(i).getFirstname(), users.get(i).getLastname(), users.get(i).getEmail(), FileHandler.getRandomPassword()});
            }
        }
        return rowsAffected;
    }

    @Transactional
    public int registerTeacherUsers(List<TeacherUserBasic> teacherUsers) { //Implementation is not optimal, will get much slower over time and with large users list
        String getIdWithMail = "SELECT id from User WHERE email=?";
        String insertUser = "INSERT INTO User (firstname, lastname, email, password) VALUES (?,?,?,?)";
        String insertTeacherUser = "INSERT IGNORE INTO TeacherUser (id) VALUES (?)";
        Integer id;
        int rowsAffected = 0;

        for(int i = 0; i < teacherUsers.size(); i++) {
            logger.info("Inserting user with email " + teacherUsers.get(i).getEmail());
            try{
                id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, teacherUsers.get(i).getEmail()); //Throws exception if no user has given mail
                rowsAffected += jdbcTemplate.update(insertTeacherUser, id);
            }catch (EmptyResultDataAccessException e){
                //Guaranteed no user is registered with given mail, adding to both user and teacher
                rowsAffected += jdbcTemplate.update(insertUser,
                        new Object[] {teacherUsers.get(i).getFirstname(), teacherUsers.get(i).getLastname(), teacherUsers.get(i).getEmail(), FileHandler.getRandomPassword()});
                id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, teacherUsers.get(i).getEmail());
                rowsAffected += jdbcTemplate.update(insertTeacherUser, id);
            }
        }
        return rowsAffected;
    }

    @Transactional
    public int registerTAUsers(List<TAUserBasic> tAUsers) { //Implementation is not optimal, will get much slower over time and with large users list
        String getIdWithMail = "SELECT id from User WHERE email=?";
        String insertUser = "INSERT INTO User (firstname, lastname, email, password) VALUES (?,?,?,?)";
        String insertTeacherUser = "INSERT IGNORE INTO TAUser (id) VALUES (?)";
        Integer id;
        int rowsAffected = 0;

        for(int i = 0; i < tAUsers.size(); i++) {
            logger.info("Inserting user with email " + tAUsers.get(i).getEmail());
            try{
                id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, tAUsers.get(i).getEmail()); //Throws exception if no user has given mail
                rowsAffected += jdbcTemplate.update(insertTeacherUser, id);
            }catch (EmptyResultDataAccessException e){
                //Guaranteed no user is registered with given mail, adding to both user and teacher
                rowsAffected += jdbcTemplate.update(insertUser,
                        new Object[] {tAUsers.get(i).getFirstname(), tAUsers.get(i).getLastname(), tAUsers.get(i).getEmail(), FileHandler.getRandomPassword()});
                id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, tAUsers.get(i).getEmail());
                rowsAffected += jdbcTemplate.update(insertTeacherUser, id);
            }
        }
        return rowsAffected;
    }

    @Transactional
    public int registerStudentUsers(List<StudentUserBasic> studentUsers) { //Implementation is not optimal, will get much slower over time and with large users list
        String getIdWithMail = "SELECT id from User WHERE email=?";
        String insertUser = "INSERT INTO User (firstname, lastname, email, password) VALUES (?,?,?,?)";
        String insertTeacherUser = "INSERT IGNORE INTO StudentUser (id) VALUES (?)";
        Integer id;
        int rowsAffected = 0;

        for(int i = 0; i < studentUsers.size(); i++) {
            logger.info("Inserting user with email " + studentUsers.get(i).getEmail());
            try{
                id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, studentUsers.get(i).getEmail()); //Throws exception if no user has given mail
                rowsAffected += jdbcTemplate.update(insertTeacherUser, id);
            }catch (EmptyResultDataAccessException e){
                //Guaranteed no user is registered with given mail, adding to both user and teacher
                rowsAffected += jdbcTemplate.update(insertUser,
                        new Object[] {studentUsers.get(i).getFirstname(), studentUsers.get(i).getLastname(), studentUsers.get(i).getEmail(), FileHandler.getRandomPassword()});
                id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, studentUsers.get(i).getEmail());
                rowsAffected += jdbcTemplate.update(insertTeacherUser, id);
            }
        }
        return rowsAffected;
    }

    @Transactional
    public int registerAdminUsers(List<AdminUserBasic> adminUsers) { //Implementation is not optimal, will get much slower over time and with large users list
        String getIdWithMail = "SELECT id from User WHERE email=?";
        String insertUser = "INSERT INTO User (firstname, lastname, email, password) VALUES (?,?,?,?)";
        String insertTeacherUser = "INSERT IGNORE INTO AdminUser (id) VALUES (?)";
        Integer id;
        int rowsAffected = 0;

        for(int i = 0; i < adminUsers.size(); i++) {
            logger.info("Inserting user with email " + adminUsers.get(i).getEmail());
            try{
                id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, adminUsers.get(i).getEmail()); //Throws exception if no user has given mail
                rowsAffected += jdbcTemplate.update(insertTeacherUser, id);
            }catch (EmptyResultDataAccessException e){
                //Guaranteed no user is registered with given mail, adding to both user and teacher
                rowsAffected += jdbcTemplate.update(insertUser,
                        new Object[] {adminUsers.get(i).getFirstname(), adminUsers.get(i).getLastname(), adminUsers.get(i).getEmail(), FileHandler.getRandomPassword()});
                id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, adminUsers.get(i).getEmail());
                rowsAffected += jdbcTemplate.update(insertTeacherUser, id);
            }
        }
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
