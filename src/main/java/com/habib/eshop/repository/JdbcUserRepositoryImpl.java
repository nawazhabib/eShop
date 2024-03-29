package com.habib.eshop.repository;

import com.habib.eshop.domain.User;
import com.habib.eshop.jdbc.ConnectionPool;
import com.habib.eshop.jdbc.JDBCTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserRepositoryImpl implements UserRepository{
    private JDBCTemplate jdbcTemplate = new JDBCTemplate();

    private final static String SAVE_USER = "INSERT INTO user " +
            "(username, " +
            " password, " +
            " version, " +
            " date_created, " +
            " date_last_updated, " +
            " email, " +
            " first_name, " +
            " last_name) " +
            " VALUES (?,?,?,?,?,?,?,?)";

    private final static String SELECT_BY_USERNAME = "SELECT " +
            " id, " +
            " username, " +
            " password, " +
            " version, " +
            " date_created, " +
            " date_last_updated, " +
            " email, " +
            " first_name, " +
            " last_name FROM user WHERE username = ?";

    @Override
    public void save(User user) {

        jdbcTemplate.executeInsertQuery(SAVE_USER,
                user.getUsername(),
                user.getPassword(),
                0L,
                (user.getDateCreated()),
                (user.getDateLastUpdated()),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    @Override
    public Optional<User> findByUsername(String username) {
        var users = jdbcTemplate.queryForObject(SELECT_BY_USERNAME,
                username, this::extractUser);

        return users.size() > 0
                ? Optional.of(users.get(0))
                : Optional.empty();
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        var user = new User();
        user.setId(resultSet.getLong("id"));
        user.setVersion(resultSet.getLong("version"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setDateCreated(resultSet.getTimestamp("date_created")
                .toLocalDateTime());
        user.setDateLastUpdated(resultSet.getTimestamp("date_last_updated")
                .toLocalDateTime());
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));

        return user;
    }
 }
