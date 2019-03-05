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
    public boolean remove(Libro libro) {
        String query = "DELETE FROM libro WHERE id = ?";
        boolean result = false;
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1,libro.getId());
            result = st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Long update(Libro libro) {
        long ris=0;
        String query = "UPDATE libro SET titolo=?, autore=?, categoria=?, codice=?, descrizione=? WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1,libro.getTitolo());
            st.setString(2,libro.getAutore());
            st.setString(3,libro.getCategoria());
            st.setString(4,libro.getCodice());
            st.setString(5,libro.getDescrizione());
            st.setLong(6, libro.getId());

            ris = st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ris;
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
