package it.evolvere.dao;

import it.evolvere.model.Utente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtenteDao extends AbstractDao<Utente>{

    public UtenteDao(Connection connection) {
        super(connection);
    }

    @Override
    public Integer count() {
        return null;
    }

    @Override
    public Long add(Utente utente) {
        return null;
    }

    @Override
    public boolean remove(Utente utente) {
        return false;
    }

    @Override
    public Long update(Utente utente) {
        return null;
    }

    @Override
    public Utente findOne(Long id) {
        return null;
    }

    @Override
    public List<Utente> findByName(String name) {
        List<Utente> utenti = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM utente WHERE cognome like '"+ name +"%'");
            while(rs.next()){
                Utente utente = new Utente();
                utente.setId(rs.getLong("id"));
                utente.setCognome(rs.getString("cognome"));
                utente.setNome(rs.getString("nome"));
                utente.setRuolo(rs.getString("ruolo"));
                utenti.add(utente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utenti;
    }
}
