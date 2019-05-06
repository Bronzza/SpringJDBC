package application.service;

import java.util.List;

public interface ServiceForEntities<T, S extends T> {
    public abstract List<? extends T> getAll();

    public abstract List<? extends T> get(T object);

    public abstract void delete(S object);

    public abstract void save (S object);

    public abstract void update (S object);
}
