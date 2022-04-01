package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import no.ntnu.idatt2105.gr13.qs3backend.util.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
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
    public int insertUsers(List<UserBasic> users) { //Implementation is not optimal, will get much slower over time and with large users list
        String getIdWithMail = "SELECT id from User WHERE email=?";
        String insertUser = "INSERT INTO User (firstname, lastname, email, password) VALUES(?,?,?,?)";
        Integer id;
        int rowsAffected = 0;

        for(int i = 0; i < users.size(); i++) {
            try{
                id = jdbcTemplate.queryForObject(getIdWithMail, Integer.class, users.get(i).getEmail());
            }catch (EmptyResultDataAccessException e){
                //Guaranteed no user is registered with given mail
                rowsAffected += jdbcTemplate.update(insertUser,
                        new Object[] {users.get(i).getFirstname(), users.get(i).getLastname(), users.get(i).getEmail(), FileHandler.getRandomPassword()});
            }
        }
        return rowsAffected;
    }


    public void test(){
        String query = "SELECT id from User WHERE email='hei@paa.meg'";
        Integer id = jdbcTemplate.queryForObject(query, Integer.class);
        logger.info("Id: "+String.valueOf(id));
    }
}
