package it.evolvere.dao;

import it.evolvere.model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDao extends AbstractDao<Libro> {

    public LibroDao(Connection connection) {
        super(connection);
    }

    @Override
    public Integer count() {
        return null;
    }

    @Override
    public Long add(Libro libro) {
        return null;
    }

    @Override
    public void remove(Libro libro) {

    }

    @Override
    public Long update(Libro libro) {
        return null;
    }

    @Override
    public Libro findOne(Long id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM libro WHERE id=?");
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                Libro libro = extractFromResultSet(rs);
                return libro;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Libro> findByName(String name) {
        List<Libro> libri = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM libro WHERE titolo LIKE '%'");
            while(rs.next())
            {
                Libro libro = extractFromResultSet(rs);
                libri.add(libro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return libri;
    }

    private Libro extractFromResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String titolo = rs.getString("titolo");
        String autore = rs.getString("autore");
        String descrizione = rs.getString("descrizione");
        String codice = rs.getString("codice");
        String categoria = rs.getString("categoria");
        Libro l = new Libro(id, titolo, descrizione, autore);
        l.setCodice(codice);
        l.setCategoria(categoria);
        return l;
    }
}
