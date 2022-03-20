package com.habib.eshop.jdbc;

import java.sql.SQLException;

public interface ObjectRowMapper<E> {
    E mapRowToObject(ResultSet resultSet) throws SQLException;
}
