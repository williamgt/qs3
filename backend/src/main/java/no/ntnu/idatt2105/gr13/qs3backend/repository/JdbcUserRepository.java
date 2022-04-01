package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserDB;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserPerson;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserPersonAll;
import no.ntnu.idatt2105.gr13.qs3backend.util.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
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
    public List<UserDB> findAll() {
        return jdbcTemplate.query("SELECT * from User", (rs, rowNum) ->
                new UserDB(
                        rs.getString("email"),
                        "",
                        rs.getInt("personId")
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

    @Transactional
    @Override
    public int insertUsers(List<User> users) { //Implementation is not optimal, will get much slower over time and with large users list
        Iterator<User> it = users.iterator();
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

    Logger logger = LoggerFactory.getLogger(JdbcUserRepository.class);

    public void test(){
        String query = "SELECT id from User WHERE email='hei@paa.meg'";
        Integer id = jdbcTemplate.queryForObject(query, Integer.class);
        logger.info("Id: "+String.valueOf(id));
    }
}
