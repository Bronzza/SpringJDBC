package dao;

import java.util.List;

interface Dao<T> {

    public abstract List<T> getAll();

    public abstract List<T> get(String surname);

    public abstract void delete(String surname);

    public abstract void save (T object);

    public abstract void update (T object);
}
