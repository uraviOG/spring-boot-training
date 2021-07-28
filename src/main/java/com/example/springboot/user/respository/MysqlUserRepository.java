package com.example.springboot.user.respository;


import com.example.springboot.user.respository.model.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Component
@Validated
public class MysqlUserRepository implements UserRepository {

    private static final Logger logger = LogManager.getLogger(MysqlUserRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MysqlUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<UserDAO> getUserById(String id) {
        String query = "SELECT * FROM users WHERE id=?";
        UserDAO userDAO = null;
        try {
            userDAO = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(UserDAO.class), id);
        } catch (DataAccessException e) {
            logger.error("No rows found :: ", e);
        }
        return Optional.ofNullable(userDAO);
    }

    @Override
    public List<UserDAO> getUsers() {
        String query = "SELECT * FROM users";
        List<UserDAO> userDAOList = jdbcTemplate.queryForList(query, UserDAO.class);
        return userDAOList;
    }

    @Override
    public void deleteUser(UserDAO userDAO) {
        String query = "DELETE FROM users WHERE id=?";
        jdbcTemplate.update(query, userDAO.getId());
    }

    @Override
    public UserDAO createUser(final UserDAO userDAO) {
        String sql = "INSERT INTO users (id, name, email, type) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, userDAO.getId(), userDAO.getName(), userDAO.getEmail(), userDAO.getType());
        return userDAO;
    }
}
