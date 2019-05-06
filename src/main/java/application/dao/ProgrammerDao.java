package application.dao;

import application.entities.Programmer;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Log4j
@Repository
public class ProgrammerDao implements DaoProgramer {

    private final static String GET_ALL_PROGRAMMER = "SELECT * FROM PROGRAMMERS";
    private final static String GET_PROGRAMMER_BY_SURNAME = "SELECT * FROM PROGRAMMERS WHERE surname = ?";
    private final static String DELETE_PROGRAMMER_BY_SURNAME = "DELETE FROM PROGRAMMERS WHERE SURNAME = ?";
    private final static String SAVE_PROGRAMMER = "INSERT INTO PROGRAMMERS (name, surname) VALUES (?,?)";
    private final static String UPDATE_PROGRAMMER = "UPDATE PROGRAMMERS SET name = ?, surname = ? WHERE id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setdataSource(DataSource dataSource) {
    }

    public List<Programmer> getAll() {
        return jdbcTemplate.query(GET_ALL_PROGRAMMER, new ProgrammerMapper());
    }

    public Programmer get(String surname) {
        Programmer result = (Programmer) jdbcTemplate.queryForObject(GET_PROGRAMMER_BY_SURNAME, new Object[]{surname},
                new ProgrammerMapper());
        return result;

    }

    public void delete(String surname) {
        jdbcTemplate.update(DELETE_PROGRAMMER_BY_SURNAME, surname);
    }

    public void save(Programmer programmer) {
        jdbcTemplate.update(SAVE_PROGRAMMER, programmer.getName(), programmer.getSurName());

    }

    public void update(Programmer programmer) {
        jdbcTemplate.update(UPDATE_PROGRAMMER, programmer.getName(), programmer.getSurName(), programmer.getId());
    }
}
