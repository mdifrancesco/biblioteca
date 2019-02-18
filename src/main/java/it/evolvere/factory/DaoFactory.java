package it.evolvere.factory;

import it.evolvere.dao.AbstractDao;
import it.evolvere.dao.LibroDao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class DaoFactory {

    public static String LIBRO = "libro";
    Map<String, AbstractDao> map;

    public DaoFactory(Connection connection){
            if(map == null){
                map = new HashMap<>();
                map.put(LIBRO, new LibroDao(connection));
            }
    }

    public AbstractDao getDao(String entita){
        return map.get(entita);
    }
}
