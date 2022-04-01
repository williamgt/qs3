package no.ntnu.idatt2105.gr13.qs3backend.repository.security;

import no.ntnu.idatt2105.gr13.qs3backend.model.security.Role;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
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
            System.out.println("HERE");
            return Role.UNDEFINED;
        }
    }

    @Override
    public Role getRoleOfUser(User user) {
        try{
            UserDB userdb;

            if(!((userdb = isStudent(user)) == null)){
                return Role.STUDENT;
            }
            if(!((userdb = isTeacher(user)) == null)){
                return Role.TEACHER;
            }
            if(!((userdb = isTA(user)) == null)){
                return Role.TA;
            }
            if(!((userdb = isAdmin(user)) == null)){
                return Role.ADMIN;
            }
            return Role.UNDEFINED;
        }catch (IncorrectResultSetColumnCountException  | EmptyResultDataAccessException e){
            System.out.println("HERE");
            return Role.UNDEFINED;
        }
    }

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
}
