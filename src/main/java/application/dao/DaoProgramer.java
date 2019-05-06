package application.dao;

import application.entities.Programmer;

import javax.sql.DataSource;
import java.util.List;

interface DaoProgramer {

    public abstract void setdataSource(DataSource dataSource);

    public abstract List<Programmer> getAll();

    public abstract Programmer get(Long id);

    public abstract void delete(String surname);

    public abstract void save(Programmer programmer);

    public abstract void update(Programmer programmer);
}
