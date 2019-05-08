package application.dao;

import application.entities.Programmer;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j
@Repository
public class ProgrammerDaoImpl implements DaoProgramer {

    private final static String GET_ALL_PROGRAMMER = "SELECT * FROM PROGRAMMERS";
    private final static String GET_PROGRAMMER_BY_SURNAME = "SELECT * FROM PROGRAMMERS WHERE surname = ?";
    private final static String DELETE_PROGRAMMER_BY_SURNAME = "DELETE FROM PROGRAMMERS WHERE SURNAME = ?";
    private final static String SAVE_PROGRAMMER = "INSERT INTO PROGRAMMERS (name, surname) VALUES (?,?)";
    private final static String UPDATE_PROGRAMMER = "UPDATE PROGRAMMERS SET name = ?, surname = ? WHERE id = ?";

    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ApplicationContext context;

    @Autowired
    public void setDataSource(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setdataSource(DataSource dataSource) {
    }

    public List<Programmer> getAll() {
        List<Programmer> result = new ArrayList<>();
        jdbcTemplate.query(GET_ALL_PROGRAMMER, new ProgrammerMapper()).forEach(a -> {
            Programmer bean = new Programmer();
            resertFieldsFromProgramer((Programmer) a, bean);
            result.add(bean);
        });
        return result;
    }

    public Optional<Programmer> get(String surname) {
        Programmer fromDB = (Programmer) jdbcTemplate.queryForObject(GET_PROGRAMMER_BY_SURNAME, new Object[]{surname},
                new ProgrammerMapper());
        Programmer result = new Programmer();
        resertFieldsFromProgramer(fromDB, result);
        return Optional.of(result);
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

    private void resertFieldsFromProgramer(Programmer programmerFrom, Programmer programmerTo) {
        programmerTo.setName(programmerFrom.getName());
        programmerTo.setSurName(programmerFrom.getSurName());
        programmerTo.setId(programmerFrom.getId());
    }
}
