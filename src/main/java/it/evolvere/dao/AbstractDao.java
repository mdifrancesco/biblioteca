package it.evolvere.dao;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDao<T> {

    protected Connection connection;

    public AbstractDao(Connection connection){
        this.connection = connection;
    }

    public abstract Integer count();

    public abstract Long add(T t);

    public abstract void remove(T t);

    public abstract Long update(T t);

    public abstract T findOne(Long id);

    public abstract List<T> findByName(String name);
}
