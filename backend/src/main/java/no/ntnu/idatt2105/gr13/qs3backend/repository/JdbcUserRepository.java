package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserDB;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserPerson;
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
    public List<UserPerson> findAll() {
        return jdbcTemplate.query("SELECT * from User", BeanPropertyRowMapper.newInstance(UserPerson.class));
    }

    @Override
    public UserPerson findById(long id) {
        try{
            String query = "SELECT * from User, Person where User.personId = Person.id and User.personId = ?";
            UserDB user = jdbcTemplate.queryForObject(query, (rs, rowNum) ->
                    new UserDB(
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("personId")
                    ), id);
            return user;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }
}
