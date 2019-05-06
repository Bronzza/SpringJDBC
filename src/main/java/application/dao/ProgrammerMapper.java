package application.dao;

import application.entities.Programmer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
@Getter
@Setter
public class ProgrammerMapper implements RowMapper {
    @Autowired
    ApplicationContext context;
    public Programmer mapRow(ResultSet resultSet, int i) throws SQLException {
        Programmer programmer = new Programmer();
        programmer.setId(resultSet.getLong("id"));
        programmer.setName(resultSet.getString("name"));
        programmer.setSurName(resultSet.getString("surname"));
        return programmer;
    }
}
