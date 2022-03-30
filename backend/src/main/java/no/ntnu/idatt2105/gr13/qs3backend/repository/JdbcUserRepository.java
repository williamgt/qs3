package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserDB;
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
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * from User", BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public UserDB findById(long id) {
        try{
            String query = "SELECT * from User where User.id = ?";
            UserDB user = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(UserDB.class), id);
            return user;
        }catch (IncorrectResultSetColumnCountException e){
            return null;
        }
    }
}
