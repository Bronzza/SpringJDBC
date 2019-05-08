package application.dao;

import application.entities.Programmer;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

interface DaoProgramer {

    abstract void setdataSource(DataSource dataSource);

    abstract List<Programmer> getAll();

    abstract Optional<Programmer> get(String name);

    abstract void delete(String surname);

    abstract void save(Programmer programmer);

    abstract void update(Programmer programmer);
}
