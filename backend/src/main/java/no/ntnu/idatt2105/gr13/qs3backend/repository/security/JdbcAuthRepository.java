package no.ntnu.idatt2105.gr13.qs3backend.repository.security;

import no.ntnu.idatt2105.gr13.qs3backend.model.security.Role;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserDB;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcAuthRepository implements AuthRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserRole getUserWithRole(User user) {
        try{
            UserDB userdb;
            if((userdb = isStudent(user)) == null){
                return new UserRole(userdb, Role.STUDENT);
            }
            if((userdb = isTeacher(user)) == null){
                return new UserRole(userdb, Role.TEACHER);
            }
            if((userdb = isTA(user)) == null){
                return new UserRole(userdb, Role.TA);
            }
            if((userdb = isAdmin(user)) == null){
                return new UserRole(userdb, Role.ADMIN);
            }
            return null;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }

    private UserDB isAdmin(User user) {
        try{
            String query = "SELECT * from User, AdminUser where User.email = AdminUser.email and AdminUser.email =?";
            UserDB userdb = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(UserDB.class), user.getEmail());
            return userdb;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }

    private UserDB isTA(User user) {
        try{
            String query = "SELECT * from User, TAUser where User.email = TAUser.email and TAUser.email =?";
            UserDB userdb = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(UserDB.class), user.getEmail());
            return userdb;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }

    private UserDB isTeacher(User user) {
        try{
            String query = "SELECT * from User, TeacherUser where User.email = TeacherUser.email and TeacherUser.email =?";
            UserDB userdb = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(UserDB.class), user.getEmail());
            return userdb;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }

    private UserDB isStudent(User user) {
        try{
            String query = "SELECT * from User, StudentUser where User.email = StudentUser.email and StudentUser.email =?";
            UserDB userdb = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(UserDB.class), user.getEmail());
            return userdb;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }
}
