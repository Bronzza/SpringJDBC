package application.dao;

import application.entities.Programmer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgrammerMapper implements RowMapper {
    public Programmer mapRow(ResultSet resultSet, int i) throws SQLException {
        Programmer programmer = new Programmer();
        programmer.setId(resultSet.getLong("id"));
        programmer.setName(resultSet.getString("name"));
        programmer.setSurName(resultSet.getString("surname"));
        return programmer;
    }
}
