package no.ntnu.idatt2105.gr13.qs3backend.repository.security;

import no.ntnu.idatt2105.gr13.qs3backend.model.security.Role;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserDB;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcAuthRepository implements AuthRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * DEPRECATED.
     * Previous method for authorizing users when logging in. Replaced by better ways of authorizing logins.
     * @param user the user
     * @return role of user logging in
     */
    @Override
    public Role getUserWithRole(User user) {
        try{
            UserDB userdb;

            if(!((userdb = isStudent(user)) == null)){
                if(user.getPassword().equals(userdb.getPassword()))
                    return Role.STUDENT;
            }
            if(!((userdb = isTeacher(user)) == null)){
                if(user.getPassword().equals(userdb.getPassword()))
                    return Role.TEACHER;
            }
            if(!((userdb = isTA(user)) == null)){
                if(user.getPassword().equals(userdb.getPassword()))
                    return Role.TA;
            }
            if(!((userdb = isAdmin(user)) == null)){
                if(user.getPassword().equals(userdb.getPassword()))
                    return Role.ADMIN;
            }
            return Role.UNDEFINED;
        }catch (IncorrectResultSetColumnCountException  | EmptyResultDataAccessException e){
            return Role.UNDEFINED;
        }
    }

    /**
     * Gets the role of a given user.
     * @param user the user
     * @return the users role
     */
    @Override
    public Role getRoleOfUser(User user) {
        try{

            if(!(isStudent(user) == null)){
                return Role.STUDENT;
            }
            if(!(isTeacher(user) == null)){
                return Role.TEACHER;
            }
            if(!(isTA(user) == null)){
                return Role.TA;
            }
            if(!(isAdmin(user) == null)){
                return Role.ADMIN;
            }
            return Role.UNDEFINED;
        }catch (IncorrectResultSetColumnCountException  | EmptyResultDataAccessException e){
            return Role.UNDEFINED;
        }
    }

    /**
     * Helper method that checks whether a user is an admin or not.
     * @param user user to be checked
     * @return the user if it is an admin, null if not
     */
    private UserDB isAdmin(User user) {
        try{
            String query = "SELECT * from User, AdminUser where User.id = AdminUser.id and User.email =?";
            UserDB userdb = jdbcTemplate.queryForObject(query, (rs, rowNum) -> new UserDB(
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getInt("id")
                    )
                    , user.getEmail());
            return userdb;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    /**
     * Helper method that checks whether a user is a teaching assistant or not.
     * @param user user to be checked
     * @return the user if it is a teaching assistant, null if not
     */
    private UserDB isTA(User user) {
        try{
            String query = "SELECT * from User, TAUser where User.id = TAUser.id and User.email =?";
            UserDB userdb = jdbcTemplate.queryForObject(query, (rs, rowNum) -> new UserDB(
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("id")
                    )
                    , user.getEmail());
            return userdb;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    /**
     * Helper method that checks whether a user is a teacher or not.
     * @param user user to be checked
     * @return the user if it is a teacher, null if not
     */
    private UserDB isTeacher(User user) {
        try{
            String query = "SELECT * from User, TeacherUser where User.id = TeacherUser.id and User.email =?";
            UserDB userdb = jdbcTemplate.queryForObject(query, (rs, rowNum) -> new UserDB(
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("id")
                    )
                    , user.getEmail());
            return userdb;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    /**
     * Helper method that checks whether a user is a student or not.
     * @param user to be checked if student
     * @return the user if it is a student, null if not
     */
    private UserDB isStudent(User user) {
        try{
            String query = "SELECT * from User, StudentUser where User.id = StudentUser.id and User.email =?";
            UserDB userdb = jdbcTemplate.queryForObject(query, (rs, rowNum) -> new UserDB(
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("id")
                    )
                    , user.getEmail());
            return userdb;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Authorizes a user that tries to log in. Throws BadCredentialsException if wrong email or password.
     * @param user the user that tries to log in
     * @return A user from the DB
     */
    @Override
    public UserLogin authUser(UserLogin user){
        try{
            String query = "SELECT * from User where User.email=?";
            UserLogin userdb = jdbcTemplate.queryForObject(query, (rs, rowNum) -> new UserLogin(
                            rs.getString("email"),
                            rs.getString("password")
                    )
                    , user.getEmail());
            return userdb;
        }catch (Exception e){
            throw new BadCredentialsException("Wrong email or password");
        }
    }
}
