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
    public Role getUserWithRole(User user) {
        try{
            UserDB userdb;

            if(!((userdb = isStudent(user)) == null)){
                if(user.getPassword().equals(userdb.getPassword()))
                    return Role.STUDENT;
            }
            if((userdb = isTeacher(user)) == null){
                if(user.getPassword().equals(userdb.getPassword()))
                    return Role.TEACHER;
            }
            if((userdb = isTA(user)) == null){
                if(user.getPassword().equals(userdb.getPassword()))
                    return Role.TA;
            }
            if((userdb = isAdmin(user)) == null){
                if(user.getPassword().equals(userdb.getPassword()))
                    return Role.ADMIN;
            }
            return null;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }

    private UserDB isAdmin(User user) {
        try{
            String query = "SELECT * from User, AdminUser where User.email = AdminUser.email and AdminUser.email =?";
            UserDB userdb = jdbcTemplate.queryForObject(query, (rs, rowNum) -> new UserDB(
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getInt("personId")
                    )
                    , user.getEmail());
            return userdb;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }

    private UserDB isTA(User user) {
        try{
            String query = "SELECT * from User, TAUser where User.email = TAUser.email and TAUser.email =?";
            UserDB userdb = jdbcTemplate.queryForObject(query, (rs, rowNum) -> new UserDB(
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("personId")
                    )
                    , user.getEmail());
            return userdb;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }

    private UserDB isTeacher(User user) {
        try{
            String query = "SELECT * from User, TeacherUser where User.email = TeacherUser.email and TeacherUser.email =?";
            UserDB userdb = jdbcTemplate.queryForObject(query, (rs, rowNum) -> new UserDB(
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("personId")
                    )
                    , user.getEmail());
            return userdb;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }

    private UserDB isStudent(User user) {
        try{
            String query = "SELECT * from User, StudentUser where User.email = StudentUser.email and StudentUser.email =?";
            UserDB userdb = jdbcTemplate.queryForObject(query, (rs, rowNum) -> new UserDB(
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("personId")
                    )
                    , user.getEmail());
            return userdb;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }
}
