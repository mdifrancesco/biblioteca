package it.evolvere.controller;

import it.evolvere.dao.LibroDao;
import it.evolvere.dao.UtenteDao;
import it.evolvere.factory.ConnectionFactory;
import it.evolvere.factory.DaoFactory;
import it.evolvere.model.Libro;
import it.evolvere.model.Utente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.util.List;

public class BibliotecaController {

    private DaoFactory daoFactory;

    @FXML
    private Label gnazio;

    @FXML private ListView<Libro> libri;

    @FXML private TextField categoria;

    @FXML private TextField codice;

    @FXML private TextField titolo;

    @FXML private TextField autore;

    @FXML private TextArea descrizione;

    @FXML private void selezionaLibro(MouseEvent event){
        Libro libro = libri.getSelectionModel().getSelectedItem();
        categoria.setText(libro.getCategoria());
        codice.setText(libro.getCodice());
        titolo.setText(libro.getTitolo());
        autore.setText(libro.getAutore());
        descrizione.setText(libro.getDescrizione());
    }

    @FXML
    private void aggiungiLibro(){
        categoria.setText("");
        codice.setText("");
        titolo.setText("");
        autore.setText("");
        descrizione.setText("");
    }

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        gnazio.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");

        Connection connection = ConnectionFactory.getConnection();

        //1. Pattern Factory:



        //Data Access Object (D.A.O.)
        DaoFactory daoFactory = new DaoFactory(connection);
        LibroDao libroDao = (LibroDao) daoFactory.getDao(DaoFactory.LIBRO);
        UtenteDao utenteDao = (UtenteDao) daoFactory.getDao(DaoFactory.UTENTE);

        List<Libro> list = libroDao.findByName("");
        for(Libro libro : list){
            libri.getItems().add(libro);
        }

        List<Utente> listUtenti = utenteDao.findByName("");
        System.out.println(listUtenti.size());
    }
}
