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
        String query = "insert into libro (titolo, autore, categoria, codice, descrizione) values(?,?,?,?,?)";
        String queryRecuperoLibro = "select * from libro where codice = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,libro.getTitolo());
            statement.setString(2,libro.getAutore());
            statement.setString(3,libro.getCategoria());
            statement.setString(4,libro.getCodice());
            statement.setString(5,libro.getDescrizione());

            statement.execute();
            statement.close();

            statement = connection.prepareStatement(queryRecuperoLibro);
            statement.setString(1, libro.getCodice());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                long id = rs.getLong("id");
                libro.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return libro.getId();
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
