package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
                        rs.getInt("personId")
                ));
    }

    @Override
    public List<UserPersonAll> findAllDetails() {
        return jdbcTemplate.query("SELECT * from User, Person where User.personId = Person.id", (rs, rowNum) ->
                new UserPersonAll(
                        rs.getString("email"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("personId"),
                        rs.getString("password")
                ));
    }

    @Override
    public UserPerson findById(long id) {
        try{
            String query = "SELECT * from User, Person where User.personId = Person.id and User.personId = ?";
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
    public UserPersonAll findByIdAdmin(long id) {
        try{
            String query = "SELECT * from User, Person where User.personId = Person.id and User.personId = ?";
            UserPersonAll user = jdbcTemplate.queryForObject(query, (rs, rowNum) ->
                    new UserPersonAll(
                            rs.getString("email"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getInt("id"),
                            rs.getString("password")
                    ), id);
            return user;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }
}
