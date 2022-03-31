package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JdbcUserRepository implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
                        rs.getString("lastname")
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
}
