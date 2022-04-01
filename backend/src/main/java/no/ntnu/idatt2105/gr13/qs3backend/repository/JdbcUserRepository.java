package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
